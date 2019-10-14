package com.spring.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.dto.FestivalVO;
import com.spring.dto.ReviewVO;

public interface MainPageService {
	public List<FestivalVO> FestivalListToMain() throws SQLException;
	
	public List<ReviewVO> ReviewListToMain() throws SQLException;
	
	public List<FestivalVO> Vote1ListToMain() throws SQLException;
	
	public List<ReviewVO> LikeListToMain() throws SQLException;

}