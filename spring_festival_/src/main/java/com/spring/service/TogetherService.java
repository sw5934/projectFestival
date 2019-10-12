package com.spring.service;

import java.sql.SQLException;
import java.util.Map;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.TogetherVO;

public interface TogetherService {
	
	//리스트조회
	public Map<String, Object> getList(SearchCriteria cri) throws SQLException;
	
	//글조회
	public TogetherVO getTogether(int tno) throws SQLException;
	
	//글작성
	public void regist(TogetherVO together) throws SQLException;
	
	//글수정
	public void modify(TogetherVO together) throws SQLException;
	
	//글삭제
	public void remove(int tno) throws SQLException;
	
	//글읽기(조회수증가)
	public Map<String, Object> read(int tno, SearchCriteria cri) throws SQLException;
	
	//글가져오기
	public TogetherVO get(int tno) throws SQLException;
	
	public int getNextUnq_Id() throws SQLException;
	
	public void deadLineUpdate(String tno, String t_state) throws SQLException;
	
	
}
