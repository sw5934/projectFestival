package com.spring.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.ReviewVO;

public interface ReviewService {

	// 리스트조회
	public Map<String, Object> getList(SearchCriteria cri) throws SQLException;

	// 글조회
	public ReviewVO getReview(int rno) throws SQLException;

	// 글작성
	public void regist(ReviewVO review) throws SQLException;

	// 글수정
	public void modify(ReviewVO review) throws SQLException;

	// 글삭제
	public void remove(int rno) throws SQLException;

	// 글읽기(조회수증가)
	public Map<String, Object> read(int rno, SearchCriteria cri) throws SQLException;
	
	// 글 가져오기
	public ReviewVO get(int rno) throws SQLException;

}
