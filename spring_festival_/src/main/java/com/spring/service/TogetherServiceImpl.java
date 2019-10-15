package com.spring.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.controller.board.PageMaker;
import com.spring.controller.board.SearchCriteria;
import com.spring.dao.CommentsDAO;
import com.spring.dao.FestivalDAO;
import com.spring.dao.MemberDAO;
import com.spring.dao.TogetherDAO;
import com.spring.dto.FestivalVO;
import com.spring.dto.MemberVO;
import com.spring.dto.TogetherVO;

public class TogetherServiceImpl implements TogetherService{
	
	private TogetherDAO togetherDAO;
	public void setTogetherDAO(TogetherDAO togetherDAO) {
		this.togetherDAO = togetherDAO;
	}
	
	private FestivalDAO festivalDAO;
	public void setFestivalDAO(FestivalDAO festivalDAO) {
		this.festivalDAO = festivalDAO;
	}
	
	private CommentsDAO commentsDAO;
	public void setCommentsDAO(CommentsDAO commentsDAO) {
		this.commentsDAO = commentsDAO;
	}
	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public Map<String, Object> getList(SearchCriteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object> ();
		List<TogetherVO> togetherList = togetherDAO.selectTogetherCriteria(cri);
		
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		
		Date todayDate = new Date();
		
		for(TogetherVO together : togetherList) {
			if(commentsDAO.countComments(together.getUnq_Id()) != 0) {
				together.setCommentcount(commentsDAO.countComments(together.getUnq_Id()));					
			}
			Date t_regDate = together.getT_regDate();
			
			if(fm.format(t_regDate).equals(fm.format(todayDate))) {
				together.setNewCount(2);
			}
		}
			
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		pageMaker.setTotalCount(togetherDAO.selectTogetherCriteriaTotalCount(cri));
		dataMap.put("togetherlist", togetherList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}

	@Override
	public TogetherVO getTogether(int tno) throws SQLException {
		TogetherVO together = togetherDAO.selectTogetherByTno(tno);		
		
		
		return together;
	}

	@Override
	public void regist(TogetherVO together) throws SQLException {
		int tno = togetherDAO.getSeqNextValue();
		together.setTno(tno);
		
		Date t_regDate = new Date();
		System.out.println("from : " +t_regDate);
		
		together.setT_regDate(t_regDate);
		
		
		togetherDAO.insertTogether(together);
	}

	@Override
	public void modify(TogetherVO together) throws SQLException {
		togetherDAO.updateTogether(together);		
		
	}

	@Override
	public void remove(int tno) throws SQLException {
		togetherDAO.deleteTogether(tno);
	}

	@Override
	public Map<String, Object> read(int tno, SearchCriteria cri) throws SQLException {
		TogetherVO together = togetherDAO.selectTogetherByTno(tno);
		
		
		together.setCommentsList(commentsDAO.selectCommentsListPage(together.getUnq_Id(), cri));

		FestivalVO festival = festivalDAO.selectFestival(together.getF_no());
		
		System.out.println("together.getF_no() : "+together.getF_no());
		System.out.println("festival : "+festival);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(commentsDAO.countComments(together.getUnq_Id()));
		
		togetherDAO.increaseViewCnt(tno);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		MemberVO writer = memberDAO.selectMemberByID(festival.getF_writer());
		dataMap.put("writer", writer);
		dataMap.put("festival", festival);
		dataMap.put("together", together);
		dataMap.put("pageMaker", pageMaker);
		return dataMap;
	}

	@Override
	public TogetherVO get(int tno) throws SQLException {	
		TogetherVO together = togetherDAO.selectTogetherByTno(tno);
		int unq_Id = together.getUnq_Id();	
		
		
		
		return together;
	}
	
	public int getNextUnq_Id() throws SQLException{
		return togetherDAO.getUnqSeqNextValue();
	}
	
	@Override
	public void deadLineUpdate(String tno, String t_state) throws SQLException{
		togetherDAO.deadLineTogether(tno, t_state);
	}	
	

}
