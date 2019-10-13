package com.spring.service;

import java.sql.SQLException;
import java.util.Map;

import com.spring.controller.board.Second_Criteria;

public interface MyPageService {
	public Map<String, Object> myReviewList(Second_Criteria cri) throws SQLException;
	public Map<String, Object> myTogetherList(Second_Criteria cri) throws SQLException;
	public Map<String, Object> commentsBoard(Second_Criteria cri) throws SQLException;
	public Map<String, Object> holdingList(Second_Criteria cri) throws SQLException;
	public Map<String, Object> authSetting(Second_Criteria cri) throws SQLException;
	public void authUpdate(Map<String, String> strMap) throws SQLException;
	
	
	
	public Map<String, Object> memSearch(Second_Criteria cri) throws SQLException;
}
