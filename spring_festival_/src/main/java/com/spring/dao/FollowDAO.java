package com.spring.dao;

import java.sql.SQLException;
import java.util.Map;

import com.spring.controller.board.Criteria;
import com.spring.controller.board.Second_Criteria;

public interface FollowDAO {
	public Map<String, Object> followList(Second_Criteria cri) throws SQLException;
	public int followTotalCount(Criteria cri) throws SQLException;
	public int followedTotalCount(Criteria cri) throws SQLException;
	public Map<String,Object> f_write(Second_Criteria cri) throws SQLException;
	public void unFollow(String nickName, String myId) throws SQLException;
	public int f_WriteTotalCount(Criteria cri) throws SQLException;
	public int f_WantGoTotalCount(Criteria cri) throws SQLException;
	
	
	
}
