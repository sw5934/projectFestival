package com.spring.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.controller.board.PageMaker;
import com.spring.controller.board.SearchCriteria;
import com.spring.dao.CommentsDAO;
import com.spring.dao.FestivalDAO;
import com.spring.dao.ReportDAO;
import com.spring.dto.FestivalVO;
import com.spring.dto.ReviewVO;
import com.spring.dto.TogetherVO;

public class FestivalServiceImpl implements FestivalService{
	
	private FestivalDAO festivalDAO;
	public void setFestivalDAO(FestivalDAO festivalDAO) {
		this.festivalDAO = festivalDAO;
	}
	
	private CommentsDAO commentsDAO;
	public void setCommentsDAO(CommentsDAO commentsDAO) {
		this.commentsDAO = commentsDAO;
	}
	
	private ReportDAO reportDAO;
	public void setReportDAO(ReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}

	@Override
	public Map<String, Object> getFestivalList(SearchCriteria cri) throws SQLException {
		List<FestivalVO> festivalList = festivalDAO.selectSearchFestivalList(cri);
		
		for(FestivalVO festival : festivalList) {
			if(commentsDAO.countComments(festival.getUnq_Id())!=0) {
				festival.setCommentCount(commentsDAO.countComments(festival.getUnq_Id()));}
			festival.setHashTagList(festivalDAO.selectTag(festival.getFno()));
			float score = festivalDAO.selectScoreAvg(festival.getFno());
			score = ((float)((int)(score*10)))/10;
			festival.setScoreAvg(score);
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
		festival.setHashTagList(festivalDAO.selectTag(festival.getFno()));
		return festival;
	}
	
	@Override
	public void regist(FestivalVO festival) throws SQLException {
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
		festival.setHashTagList(festivalDAO.selectTag(festival.getFno()));
		float score = festivalDAO.selectScoreAvg(festival.getFno());
		score = ((float)((int)(score*10)))/10;
		festival.setScoreAvg(score);
		
		System.out.println(festival.getHashTagList().toString());
		
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
	@Override
	public void clickVote(FestivalVO festival,String id,int seperate)throws SQLException{
		if (seperate==1) {
			if(festivalDAO.selectVoteRecord(festival.getUnq_Id(), id, seperate)==null) {
				festivalDAO.createVoteRecord(id, festival.getUnq_Id(), seperate);
				festivalDAO.increaseVote1(festival.getFno());	
			}else {
				festivalDAO.deleteVoteRecord(id, festival.getUnq_Id(), seperate);
				festivalDAO.decreaseVote1(festival.getFno());
			}
		}
		else {
			if(festivalDAO.selectVoteRecord(festival.getUnq_Id(), id, seperate)==null) {
				festivalDAO.createVoteRecord(id, festival.getUnq_Id(), seperate);
				festivalDAO.increaseVote2(festival.getFno());	
			}else {
				festivalDAO.deleteVoteRecord(id, festival.getUnq_Id(), seperate);
				festivalDAO.decreaseVote2(festival.getFno());
			}
		}
	}
	@Override
	public void setTag(int fno,String hashTagList) throws SQLException {
		festivalDAO.deleteTag(fno);
		String[] hashTag = hashTagList.split("#");
		for(String tag:hashTag) {
			System.out.println(tag);
			if(!tag.equals("")) {
				festivalDAO.insertTag(fno,tag);
				}
			}
	}
	
	@Override
	public int getFestivalSeqNext() throws SQLException{
		return festivalDAO.selectFestivalSeqNext();
	}

	@Override
	public void doReportFestival(String reporter, int unq_id) throws SQLException {
		reportDAO.doReport(reporter, unq_id);
		
	}

	@Override
	public void doReportComments(String reporter, int c_no) throws SQLException {
		reportDAO.deleteReportComment(c_no);
		
	}

	public List<ReviewVO> getReviewList(int fno) throws SQLException{
		return festivalDAO.selectReviewListByfno(fno);
	}
	
	public List<TogetherVO> getTogetherList(int fno) throws SQLException{
		return festivalDAO.selectTogetherListByfno(fno);
	}
	
	
}
