package com.spring.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.dao.MainPageDAO;
import com.spring.dto.FestivalVO;
import com.spring.dto.ReviewVO;

public class MainPageServiceImpl implements MainPageService {
	private MainPageDAO mainPageDAO;
	public void setMainPageDAO(MainPageDAO mainPageDAO) {
		this.mainPageDAO = mainPageDAO;
	}

	@Override
	public List<FestivalVO> FestivalListToMain() throws SQLException {
		
		List<FestivalVO>festivalList = mainPageDAO.selectFestivalListToMain();
		return festivalList;
	}

	@Override
	public List<ReviewVO>  ReviewListToMain() throws SQLException {
		List<ReviewVO> reviewList = mainPageDAO.selectReviewListToMain();
		return reviewList;
	}

	@Override
	public List<FestivalVO> Vote1ListToMain() throws SQLException {
		
		List<FestivalVO> voteList = mainPageDAO.selectVote1ToMain();
		return voteList;
	}

	@Override
	public List<ReviewVO>  LikeListToMain() throws SQLException {
		
		List<ReviewVO> likeList = mainPageDAO.selectLikeToMain();
		return likeList;
	}

}
