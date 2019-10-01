package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.controller.board.Criteria;
import com.spring.dto.SecondReviewVO;

public interface BoardDAO {
	public List<SecondReviewVO> myReviewList(Criteria cri) throws SQLException;
	public int myReviewTotalCount(Criteria cri) throws SQLException;
}
