package com.spring.service;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionException;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.FestivalVO;

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
}
