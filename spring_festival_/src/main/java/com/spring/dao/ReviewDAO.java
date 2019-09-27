package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.LikeVO;
import com.spring.dto.ReviewVO;

public interface ReviewDAO {
	public List<ReviewVO> selectReviewCriteria(SearchCriteria cri) throws SQLException;

	public int selectReviewCriteriaTotalCount(SearchCriteria cri) throws SQLException;

	public ReviewVO selectReviewByRno(int rno) throws SQLException;

	public void insertReview(ReviewVO review) throws SQLException;

	public void updateReview(ReviewVO review) throws SQLException;

	public void deleteReview(int rno) throws SQLException;

	public void increaseViewCnt(int rno) throws SQLException;

	public int getSeqNextValue() throws SQLException;
	
	public int getUnqSeqNextValue() throws SQLException;
	
	public int getLikeCount(int unq_id) throws SQLException;
	
	public int getLikeHistory(String id, int unq_id) throws SQLException;
	
	public void addLike(String id, int unq_id) throws SQLException;
	
	public void deleteLike(String id, int unq_id) throws SQLException;

}
