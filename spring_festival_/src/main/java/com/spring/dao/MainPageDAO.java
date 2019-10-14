package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.dto.FestivalVO;
import com.spring.dto.ReviewVO;

public interface MainPageDAO {
	
	public List<FestivalVO> selectFestivalListToMain() throws SQLException;
	
	public List<ReviewVO> selectReviewListToMain() throws SQLException;
	
	
	public List<FestivalVO> selectVote1ToMain() throws SQLException;
	
	public List<ReviewVO> selectLikeToMain() throws SQLException;

}
