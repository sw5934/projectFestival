package com.spring.service;

import java.sql.SQLException;
import java.util.Map;

import com.spring.controller.board.Second_Criteria;

public interface MyPageService {
	public Map<String, Object> myReviewList(Second_Criteria cri) throws SQLException;
}
