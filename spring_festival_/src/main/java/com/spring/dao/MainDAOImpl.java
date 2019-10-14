package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.AttachVO;
import com.spring.dto.FestivalVO;
import com.spring.dto.ReviewVO;
import com.spring.dto.TogetherVO;

public class MainDAOImpl implements MainDAO {
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	public List<FestivalVO> selectSearchFestivalList(SearchCriteria cri) throws SQLException{
		return session.selectList("TotalSearch-Mapper.selectSearchFestivalList",cri);
	};
	
	public List<ReviewVO> selectSearchReviewList(SearchCriteria cri) throws SQLException{
		return session.selectList("TotalSearch-Mapper.selectSearchReviewList",cri);
	};
	
	public List<TogetherVO> selectSearchTogetherList(SearchCriteria cri) throws SQLException{
		return session.selectList("TotalSearch-Mapper.selectSearchTogetherList",cri);
	};

}
