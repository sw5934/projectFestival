package com.spring.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.FestivalVO;
import com.spring.dto.HashTagVO;
import com.spring.dto.ReviewVO;
import com.spring.dto.TogetherVO;
import com.spring.dto.VoteVO;

public interface FestivalDAO {
	public List<FestivalVO> selectSearchFestivalList(SearchCriteria cri) throws SQLException;
	
	public int selectSearchFestivalListCount(SearchCriteria cri) throws SQLException;
	
	public FestivalVO selectFestival(int fno) throws SQLException;

	public int selectFestivalSeqNext() throws SQLException;

	public int selectUnqSeqNext() throws SQLException;
	
	public void insertFestival(FestivalVO festival) throws SQLException;
	
	public void updateFestival(FestivalVO festival) throws SQLException;
	
	public void deleteFestival(int fno) throws SQLException;

	public void increaseViewCnt(int fno) throws SQLException;
	
	public void increaseVote1(int fno) throws SQLException;
	
	public void increaseVote2(int fno) throws SQLException;
	
	public void decreaseVote1(int fno) throws SQLException;
	
	public void decreaseVote2(int fno) throws SQLException;
	
	public VoteVO selectVoteRecord(int fno, String id, int seperate) throws SQLException;
	
	public void createVoteRecord(String id, int unq_Id, int seperate) throws SQLException;
	
	public void deleteVoteRecord(String id, int unq_Id, int seperate) throws SQLException;
	
	public void insertTag(int fno,String tag) throws SQLException;
	
	public void deleteTag(int fno) throws SQLException;
	
	public List<HashTagVO> selectTag(int fno) throws SQLException;
	
	public List<ReviewVO> selectReviewListByfno(int fno) throws SQLException;
	
	public List<TogetherVO>  selectTogetherListByfno(int fno) throws SQLException;
	
	public float selectScoreAvg(int fno) throws SQLException;
}
