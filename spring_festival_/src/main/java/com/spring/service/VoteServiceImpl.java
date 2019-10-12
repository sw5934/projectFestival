package com.spring.service;

import java.sql.SQLException;
import java.util.Map;

import com.spring.controller.board.PageMaker;
import com.spring.controller.board.Second_Criteria;
import com.spring.dao.VoteDAO;

public class VoteServiceImpl implements VoteService {
	private VoteDAO voteDAO;

	public void setVoteDAO(VoteDAO voteDAO) {
		System.out.println("VoteServiceImpl.setVoteDAO()");
		this.voteDAO = voteDAO;
	}

	@Override
	public Map<String, Object> selectVoteList(Second_Criteria cri) throws SQLException {
		System.out.println("VoteServiceImpl.selectAllVoteList()");
		
		Map<String, Object> voteListMap = voteDAO.selectVoteList(cri);
		// Map = { "wantGoList", "goAndBackList" }
		
		
		

		PageMaker wantGoPM = new PageMaker();
		wantGoPM.setCri(cri);
		int totalCount = voteDAO.wantGoTotalCount(cri);
		wantGoPM.setTotalCount(totalCount, "wantGo");
		
		
		
		PageMaker goAndBackPM = new PageMaker();
		goAndBackPM.setCri(cri);
		totalCount = voteDAO.goAndBackTotalCount(cri);
		goAndBackPM.setTotalCount(totalCount, "goAndBack");

		voteListMap.put("goAndBackPM", goAndBackPM);
		voteListMap.put("wantGoPM", wantGoPM);
		// Map = { "wantGoList", "goAndBackList", "goAndBackPM", "wantGoPM" }
		
		
		
		return voteListMap;
	}
	
	
	public void deleteVote(Map<String, Object> strMap) throws SQLException {
		voteDAO.deleteVote(strMap);
	}
}
