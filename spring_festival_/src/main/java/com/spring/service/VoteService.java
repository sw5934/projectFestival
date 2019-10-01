package com.spring.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.spring.controller.board.Second_Criteria;
import com.spring.dto.VoteVO;

public interface VoteService {
	public Map<String, Object> selectVoteList(Second_Criteria cri) throws SQLException;
	public void deleteVote(Map<String, Object> strMap) throws SQLException;
}
