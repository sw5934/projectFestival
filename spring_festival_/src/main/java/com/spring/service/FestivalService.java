package com.spring.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.FestivalVO;
import com.spring.dto.ReviewVO;
import com.spring.dto.TogetherVO;

public interface FestivalService {

	// 리스트조회
	public Map<String, Object> getFestivalList(SearchCriteria cri) throws SQLException;

	// 글조회
	public FestivalVO getFestival(int fno) throws SQLException;

	// 글작성
	public void regist(FestivalVO festival) throws SQLException;

	// 글수정
	public void modify(FestivalVO festival) throws SQLException;

	// 글삭제
	public void remove(int fno) throws SQLException;

	// 글읽기(조회수증가)
	public Map<String, Object> read(int fno, SearchCriteria cri) throws SQLException;

	public int getNextUnqId() throws SQLException;

	public void clickVote(FestivalVO festival,String id,int seperate)throws SQLException;
	
	public void setTag(int fno,String hashTag) throws SQLException;
	
	public int getFestivalSeqNext() throws SQLException;
	
	public void doReportFestival(String reporter, int unq_id) throws SQLException;
	
	public void doReportComments(String reporter, int c_no) throws SQLException;
	
	public List<ReviewVO> getReviewList(int fno) throws SQLException;
	
	public List<TogetherVO> getTogetherList(int fno) throws SQLException;
}