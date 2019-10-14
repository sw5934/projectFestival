package com.spring.service;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionException;

import com.spring.controller.board.Second_Criteria;


public interface FollowService {
	public Map<String, Object> followList(Second_Criteria cri) throws SQLException;
	public Map<String, Object> f_write(Second_Criteria cri) throws SQLException;
	public void unFollow(String nickName, String myId) throws SQLException;
	public int checkFollow(String me, String follow) throws SQLException;
	public void doFollow(String me, String follow) throws SQLException;
}
