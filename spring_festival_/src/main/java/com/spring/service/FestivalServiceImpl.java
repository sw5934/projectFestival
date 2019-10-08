package com.spring.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionException;

import com.spring.controller.board.PageMaker;
import com.spring.controller.board.SearchCriteria;
import com.spring.dao.CommentsDAO;
import com.spring.dao.FestivalDAO;
import com.spring.dto.AttachVO;
import com.spring.dto.FestivalVO;
import com.spring.dto.ReviewVO;

public class FestivalServiceImpl implements FestivalService{
	
	private FestivalDAO festivalDAO;
	public void setFestivalDAO(FestivalDAO festivalDAO) {
		this.festivalDAO = festivalDAO;
	}
	
	private CommentsDAO commentsDAO;
	public void setCommentsDAO(CommentsDAO commentsDAO) {
		this.commentsDAO = commentsDAO;
	}

	@Override
	public Map<String, Object> getFestivalList(SearchCriteria cri) throws SQLException {
		List<FestivalVO> festivalList = festivalDAO.selectSearchFestivalList(cri);
		
		for(FestivalVO festival : festivalList) {
			if(commentsDAO.countComments(festival.getUnq_Id())!=0) {
				festival.setCommentCount(commentsDAO.countComments(festival.getUnq_Id()));}
		}
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		pageMaker.setTotalCount(festivalDAO.selectSearchFestivalListCount(cri));
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("festivalList", festivalList);
		dataMap.put("pageMaker", pageMaker);
		System.out.println(pageMaker.toString());
		return dataMap;
	}

	@Override
	public FestivalVO getFestival(int fno) throws SQLException {
		FestivalVO festival = festivalDAO.selectFestival(fno);
		return festival;
	}
	
	@Override
	public void regist(FestivalVO festival) throws SQLException {
		int fno = festivalDAO.selectFestivalSeqNext();
		festival.setFno(fno);
		festivalDAO.insertFestival(festival);
	}

	@Override
	public void modify(FestivalVO festival) throws SQLException {		
		festivalDAO.updateFestival(festival);		
	}

	@Override
	public void remove(int fno) throws SQLException {
		festivalDAO.deleteFestival(fno);		
		
	}

	@Override
	public Map<String, Object> read(int fno, SearchCriteria cri) throws SQLException {
		FestivalVO festival = festivalDAO.selectFestival(fno);
		
		festival.setCommentsList(commentsDAO.selectCommentsListPage(festival.getUnq_Id(), cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(commentsDAO.countComments(festival.getUnq_Id()));
		
		festivalDAO.increaseViewCnt(fno);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("festival", festival);
		dataMap.put("pageMaker", pageMaker);
		return dataMap;
	}
	@Override
	public int getNextUnqId() throws SQLException{
		return festivalDAO.selectUnqSeqNext();
	}
}
