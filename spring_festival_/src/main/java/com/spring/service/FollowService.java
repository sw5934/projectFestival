package com.spring.service;

import java.sql.SQLException;
import java.util.Map;

import com.spring.controller.board.Second_Criteria;


public interface FollowService {
	public Map<String, Object> followList(Second_Criteria cri) throws SQLException;
	public Map<String, Object> f_write(Second_Criteria cri) throws SQLException;
	public void unFollow(String nickName, String myId) throws SQLException;
}
