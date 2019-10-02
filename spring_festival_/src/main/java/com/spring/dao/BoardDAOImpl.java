package com.spring.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.spring.controller.board.Criteria;
import com.spring.dto.ReviewAndTogetherVO;

public class BoardDAOImpl implements BoardDAO {
	private SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	
	
	public List<ReviewAndTogetherVO> myReviewList(Criteria cri) throws SQLException {
		System.out.println("BoardDAOImpl.myReviewList() 시작");

		int offset=cri.getPageStartRowNum();
		int limit=cri.getPerPageNum();
		
		RowBounds rowBounds=new RowBounds(offset,limit);		
		
		
		
		List<ReviewAndTogetherVO> myReviewList = new ArrayList<ReviewAndTogetherVO>();
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
	
	
	
	
	public List<ReviewAndTogetherVO> myTogetherList(Criteria cri) throws SQLException {
		List<ReviewAndTogetherVO> myTogetherList = new ArrayList<ReviewAndTogetherVO>();
		
		
		return myTogetherList;
	}
	@Override
	public int myTogetherTotalCount(Criteria cri) throws SQLException {
		int count = session.selectOne("Board-Mapper.myTogetherTotalCount", cri);
		return count;
	}



}
