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
import com.spring.dto.MemberVO;

public class FollowDAOImpl implements FollowDAO {

	private SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	public Map<String,Object> followList(Second_Criteria cri) throws SQLException {
		
		int offset=cri.getPageStartRowNum_First();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);		
		
		List<MemberVO> follow = session.selectList("Follow-Mapper.selectFollow", cri, rowBounds);
		
		List<MemberVO> followed = session.selectList("Follow-Mapper.selectFollowed", cri, rowBounds);
		
		for(MemberVO member:followed) {
			System.out.println(member.toString());
		}

		Map<String,Object> dataMap = new HashMap<String, Object>();
		dataMap.put("follow", follow);
		dataMap.put("followed", followed);
		
		return dataMap;
	}
	
	public int followTotalCount(Criteria cri) throws SQLException {
		
		
		return session.selectList("Follow-Mapper.selectFollow",cri).size();
	}



	public int followedTotalCount(Criteria cri) throws SQLException {
		
		return session.selectList("Follow-Mapper.selectFollowed", cri).size();
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

		List<FollowWriteVO> wantGoList = new ArrayList<FollowWriteVO>();
		wantGoList = session.selectList("Follow-Mapper.followWantGo", cri, rowBounds);
		// 팔로워의 가고싶어요 글 목록 출력을 하기 위한 쿼리
		
		Map<String,Object> dataMap = new HashMap<String, Object>();
		dataMap.put("three_board_list", three_board_list);
		dataMap.put("wantGoList", wantGoList);
		
		return dataMap;	// FollowService에서 카운트 수행.
	}
	
	
	// 팔로워가 작성한 게시판 3개의 모든 글들의 갯수를 카운트
	public int f_WriteTotalCount(Criteria cri) throws SQLException {
		return session.selectList("Follow-Mapper.followBoard", cri).size();
	}
	
	// 팔로워가 작성한 가고싶어요 게시판의 모든 글들의 갯수를 카운트
	public int f_WantGoTotalCount(Criteria cri) throws SQLException {
		return session.selectList("Follow-Mapper.followWantGo", cri).size();
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void unFollow(String nickName, String myId) throws SQLException {
		HashMap<String,String> data = new HashMap<String,String>();
		data.put("id",nickName);
		data.put("myId",myId);
		session.update("Follow-Mapper.deleteFollow", data);
	}

	public int selectFollowThis(String me, String follow) throws SQLException{
		HashMap<String,String> data = new HashMap<String,String>();
		data.put("me",me);
		data.put("follow",follow);
		return session.selectOne("Follow-Mapper.selectFollowThis",data);
	};
	
	public void insertFollow(String me, String follow) throws SQLException{
		HashMap<String,String> data = new HashMap<String,String>();
		data.put("me",me);
		data.put("follow",follow);
		session.update("Follow-Mapper.insertFollow",data);
	};
}
