package com.spring.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.spring.controller.board.Criteria;
import com.spring.dto.CommentsBoardVO;
import com.spring.dto.ReviewAndTogetherVO;

public interface MyPageDAO {
	public List<ReviewAndTogetherVO> myReviewList(Criteria cri) throws SQLException;
	public List<ReviewAndTogetherVO> myTogetherList(Criteria cri) throws SQLException;
	public int myReviewTotalCount(Criteria cri) throws SQLException;
	public int myTogetherTotalCount(Criteria cri) throws SQLException;
	public int myCommentsTotalCount(Criteria cri) throws SQLException;
	public List<CommentsBoardVO> commentsBoard(Criteria cri) throws SQLException;
	public Map<String, Object> holdingList(Criteria cri) throws SQLException;
	public int holdingTotalCount(Criteria cri) throws SQLException;
}
