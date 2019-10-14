package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spring.dto.FestivalVO;
import com.spring.dto.ReviewVO;
import com.spring.dto.TogetherVO;

public class MainPageDAOImpl implements MainPageDAO {
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<FestivalVO> selectFestivalListToMain() throws SQLException {		
		List<FestivalVO> festivalList = session.selectList("MainPage-Mapper.selectFestivalListToMain");
		return festivalList;
	}

	@Override
	public List<ReviewVO> selectReviewListToMain() throws SQLException {
		List<ReviewVO> reviewList = session.selectList("MainPage-Mapper.selectReviewListToMain");
		return reviewList;
	}


	@Override
	public List<FestivalVO> selectVote1ToMain() throws SQLException {
		List<FestivalVO> VoteList = session.selectList("MainPage-Mapper.selectVote1ToMain");
		return VoteList;
	}

	@Override
	public List<ReviewVO> selectLikeToMain() throws SQLException {
		List<ReviewVO> LikeList = session.selectList("MainPage-Mapper.selectLikeToMain");
		return LikeList;
	}

}
