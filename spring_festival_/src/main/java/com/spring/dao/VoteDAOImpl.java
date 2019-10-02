package com.spring.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.spring.controller.board.Second_Criteria;
import com.spring.dto.VoteVO;

public class VoteDAOImpl implements VoteDAO{

	private SqlSession session;

	public void setSession(SqlSession session) {
		System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓ application.xml에 작성 ↓↓↓↓↓↓↓↓↓↓↓");
		System.out.println("VoteDAOImpl.setSession()");
		this.session = session;
	}

	@Override
	public Map<String, Object> selectVoteList(Second_Criteria cri) throws SQLException {
		int offset=cri.getPageStartRowNum_First();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);		
		List<VoteVO> wantGoList = session.selectList("Vote-Mapper.WantGoVoteList", cri, rowBounds);

		
		
		
		offset=cri.getPageStartRowNum_Second();
		limit=cri.getPerPageNum();
		rowBounds=new RowBounds(offset,limit);		
		List<VoteVO> goAndBackList = session.selectList("Vote-Mapper.GoAndBackVoteList", cri, rowBounds);		

		
		
		

		///////////////////////
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("wantGoList", wantGoList);
		dataMap.put("goAndBackList", goAndBackList);
		// Map = { "wantGoList", "goAndBackList" }
		
		
		return dataMap;
	}
	
	public int wantGoTotalCount(Second_Criteria cri) throws SQLException {
		int totalCount = session.selectOne("Vote-Mapper.WantGoTotalCount", cri);
		return totalCount;
	}
	public int goAndBackTotalCount(Second_Criteria cri) throws SQLException {
		int totalCount = session.selectOne("Vote-Mapper.GoAndBackTotalCount", cri);
		return totalCount;
	}
	
	
	
	
	
	
	
	
	
	/////////////////////////////////////////////////////////
	
	
	public void deleteVote(Map<String, Object> strMap) throws SQLException {
		System.out.println("voteDAOImpl.deleteVote(),      strMap.get('id') = " + strMap.get("id"));
		System.out.println("voteDAOImpl.deleteVote(),      strMap.get('u_id') = " + strMap.get("u_id"));
		session.update("Vote-Mapper.deleteVote", strMap);
	}
}
