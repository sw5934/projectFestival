package com.spring.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.spring.controller.board.Criteria;
import com.spring.controller.board.Second_Criteria;
import com.spring.dto.FollowWriteVO;

public class FollowDAOImpl implements FollowDAO {

	private SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	public Map<String,Object> followList(Second_Criteria cri) throws SQLException {
		int offset=cri.getPageStartRowNum_First();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);		
		List follow = new ArrayList();
		follow = session.selectList("Follow-Mapper.selectFollow", cri, rowBounds);
		
		
		
		
		
		offset=cri.getPageStartRowNum_Second();
		limit=cri.getPerPageNum();
		rowBounds=new RowBounds(offset,limit);		
		List followed = new ArrayList();
		followed = session.selectList("Follow-Mapper.selectFollowed", cri, rowBounds);


		
		
		Map<String,Object> dataMap = new HashMap<String, Object>();
		dataMap.put("follow", follow);
		dataMap.put("followed", followed);
		
		

		return dataMap;
	}
	
	public int followTotalCount(Criteria cri) throws SQLException {
		
		int followTotalCount = session.selectOne("Follow-Mapper.followTotalCount", cri);
		System.out.println("FollowDAOImpl.TotalCount - 나를 팔로워한 총 사람 수 = " + followTotalCount);
		
		return followTotalCount;
	}



	public int followedTotalCount(Criteria cri) throws SQLException {
		
		int followedTotalCount = session.selectOne("Follow-Mapper.followedTotalCount", cri);

		System.out.println("FollowDAOImpl.TotalCount - 나를 팔로워한 총 사람 수 = " + followedTotalCount);
		
		return followedTotalCount;
	}
	

	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	// 팔로워의 게시판(3개) 글 + 가고싶어요의 글을 쿼리한다.
	public Map<String,Object> f_write(Second_Criteria cri) throws SQLException {
		int offset=cri.getPageStartRowNum_First();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);		
		List<FollowWriteVO> three_board_list = new ArrayList<FollowWriteVO>();
		three_board_list = session.selectList("Follow-Mapper.followBoard", cri, rowBounds);
		// 팔오워의 게시판(3개)의 글 목록 출력을 하기 위한 쿼리

		
		
		offset=cri.getPageStartRowNum_Second();
		limit=cri.getPerPageNum();
		rowBounds=new RowBounds(offset,limit);
		List<FollowWriteVO> wantGoList = new ArrayList<FollowWriteVO>();
		wantGoList = session.selectList("Follow-Mapper.followWantGo", cri, rowBounds);
		// 팔로워의 가고싶어요 글 목록 출력을 하기 위한 쿼리

		
		
		
		Map<String,Object> dataMap = new HashMap<String, Object>();
		dataMap.put("three_board_list", three_board_list);
		dataMap.put("wantGoList", wantGoList);
		

		System.out.println("dataMap.get(three_baord_list)" + dataMap.get("three_board_list"));
		System.out.println("dataMap.get(wantGoList)" + dataMap.get("wantGoList"));


		
		return dataMap;	// FollowService에서 카운트 수행.
	}
	
	
	// 팔로워가 작성한 게시판 3개의 모든 글들의 갯수를 카운트
	public int f_WriteTotalCount(Criteria cri) throws SQLException {
		int f_WriteTotalCount = session.selectOne("Follow-Mapper.f_WriteTotalCount", cri);
		System.out.println("FollowDAOImpl.f_WriteTotalCount - 팔로워의 글 총 개수 = " + f_WriteTotalCount);
		return f_WriteTotalCount;
	}
	
	// 팔로워가 작성한 가고싶어요 게시판의 모든 글들의 갯수를 카운트
	public int f_WantGoTotalCount(Criteria cri) throws SQLException {
		int f_WantGoTotalCount = session.selectOne("Follow-Mapper.f_WantGoTotalCount", cri);
		System.out.println("FollowDAOImpl.f_WriteTotalCount - 팔로워의 글 총 개수 = " + f_WantGoTotalCount);
		return f_WantGoTotalCount;
	}
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	public void unFollow(String nickName, String myId) throws SQLException {
		System.out.println("FollowDAOImpl.unFollow() 시작,        nickName = " + nickName);
		System.out.println("FollowDAOImpl.unFollow() 시작,        myId = " + myId);
		HashMap<String,String> data = new HashMap<String,String>();
		data.put("nickName",nickName);
		data.put("myId",myId);
		System.out.println("FollowDAOImpl.unFollow(),     data(1) = " + data.get("nickName")); // nickName
		System.out.println("FollowDAOImpl.unFollow(),     data(2) = " + data.get("myId")); // myId
		System.out.println("session:" + session);
		session.update("Follow-Mapper.deleteFollow", data);
	}
}
