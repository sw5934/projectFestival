package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.FestivalVO;
import com.spring.dto.ReviewVO;
import com.spring.dto.TogetherVO;

public interface MainDAO {
	public List<FestivalVO> selectSearchFestivalList(SearchCriteria cri) throws SQLException;
	public List<ReviewVO> selectSearchReviewList(SearchCriteria cri) throws SQLException;
	public List<TogetherVO> selectSearchTogetherList(SearchCriteria cri) throws SQLException;

}
