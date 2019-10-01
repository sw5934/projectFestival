package com.spring.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.spring.controller.board.Criteria;
import com.spring.dto.ReviewVO;
import com.spring.dto.SecondReviewVO;

public class BoardDAOImpl implements BoardDAO {
	private SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	
	
	public List<SecondReviewVO> myReviewList(Criteria cri) throws SQLException {
		System.out.println("BoardDAOImpl.myReviewList() 시작");

		int offset=cri.getPageStartRowNum();
		int limit=cri.getPerPageNum();
		
		RowBounds rowBounds=new RowBounds(offset,limit);		
		
		
		
		List<SecondReviewVO> myReviewList = new ArrayList<SecondReviewVO>();
		myReviewList = session.selectList("Board-Mapper.reviewBoard", cri, rowBounds);
		
		for(int i=0; i<myReviewList.size(); i++) {
			System.out.println("BoardDAOImpl.myReviewList(),        reviewVO.get(" + i + ") = " + myReviewList.get(i));
		}
		
		
		
		System.out.println("BoardDAOImpl.myReviewList() 종료");
		return myReviewList;
	}
	
	
	public int myReviewTotalCount(Criteria cri) throws SQLException {
		int count = session.selectOne("Board-Mapper.myReviewTotalCount",cri);
		return count;
	}
	
}
