package com.spring.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.controller.board.SearchCriteria;
import com.spring.dao.CommentsDAO;
import com.spring.dao.FestivalDAO;
import com.spring.dao.MainDAO;
import com.spring.dao.ReviewDAO;
import com.spring.dto.FestivalVO;
import com.spring.dto.ReviewVO;
import com.spring.dto.TogetherVO;

public class MainServiceImpl implements MainService {
	private MainDAO mainDAO;
	public void setMainDAO(MainDAO mainDAO) {
		this.mainDAO = mainDAO;
	}
	private CommentsDAO commentsDAO;
	public void setCommentsDAO(CommentsDAO commentsDAO) {
		this.commentsDAO = commentsDAO;
	}
	private FestivalDAO festivalDAO;
	public void setFestivalDAO(FestivalDAO festivalDAO) {
		this.festivalDAO = festivalDAO;
	}
	private ReviewDAO reviewDAO;
	public void setReviewDAO(ReviewDAO reviewDAO) {
		this.reviewDAO = reviewDAO;
	}
	
	@Override
	public Map<String, Object> searchThreeBoard(SearchCriteria cri) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<FestivalVO> festivalList = mainDAO.selectSearchFestivalList(cri);
		List<ReviewVO> reviewList = mainDAO.selectSearchReviewList(cri);
		List<TogetherVO> togetherList = mainDAO.selectSearchTogetherList(cri);
		
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		
		Date todayDate = new Date();

		for(FestivalVO festival : festivalList) {
			if(commentsDAO.countComments(festival.getUnq_Id())!=0) {
				festival.setCommentCount(commentsDAO.countComments(festival.getUnq_Id()));}
			festival.setHashTagList(festivalDAO.selectTag(festival.getFno()));
			float score = festivalDAO.selectScoreAvg(festival.getFno());
			score = ((float)((int)(score*10)))/10;
			festival.setScoreAvg(score);
		}
		
		
		for(ReviewVO review : reviewList) {
			if(commentsDAO.countComments(review.getUnq_Id())!=0) {
			review.setCommentcount(commentsDAO.countComments(review.getUnq_Id()));
			}
			review.setR_like(reviewDAO.getLikeCount(review.getUnq_Id()));
			
			Date r_regDate = review.getR_regDate();
			if(fm.format(r_regDate).equals(fm.format(todayDate))) {
				review.setNewCount(2);
			}
		}
		
		for(TogetherVO together : togetherList) {
			if(commentsDAO.countComments(together.getUnq_Id()) != 0) {
				together.setCommentcount(commentsDAO.countComments(together.getUnq_Id()));					
			}
			Date t_regDate = together.getT_regDate();
			
			if(fm.format(t_regDate).equals(fm.format(todayDate))) {
				together.setNewCount(2);
			}
		}
		
		
		
		dataMap.put("festivalList",mainDAO.selectSearchFestivalList(cri));
		dataMap.put("reviewList",mainDAO.selectSearchReviewList(cri));
		dataMap.put("togetherList",mainDAO.selectSearchTogetherList(cri));
		return dataMap;
	}


}
