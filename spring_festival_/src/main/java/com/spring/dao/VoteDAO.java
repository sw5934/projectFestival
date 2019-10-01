package com.spring.dao;

import java.sql.SQLException;
import java.util.Map;

import com.spring.controller.board.Second_Criteria;

public interface VoteDAO {
	public Map<String, Object> selectVoteList(Second_Criteria cri) throws SQLException;

	public int wantGoTotalCount(Second_Criteria cri) throws SQLException;
	public int goAndBackTotalCount(Second_Criteria cri) throws SQLException;
	
	
	public void deleteVote(Map<String, Object> strMap) throws SQLException;
}
