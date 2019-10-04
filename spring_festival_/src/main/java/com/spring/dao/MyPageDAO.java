package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.controller.board.Criteria;
import com.spring.dto.ReviewAndTogetherVO;

public interface MyPageDAO {
	public List<ReviewAndTogetherVO> myReviewList(Criteria cri) throws SQLException;
	public List<ReviewAndTogetherVO> myTogetherList(Criteria cri) throws SQLException;
	public int myReviewTotalCount(Criteria cri) throws SQLException;
	public int myTogetherTotalCount(Criteria cri) throws SQLException;
}
