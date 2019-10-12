package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.TogetherVO;

public interface TogetherDAO {
	public List<TogetherVO> selectTogetherCriteria(SearchCriteria cri) throws SQLException;
	
	public int selectTogetherCriteriaTotalCount(SearchCriteria cri) throws SQLException;
	
	public TogetherVO selectTogetherByTno(int tno) throws SQLException;
	
	public void insertTogether(TogetherVO together) throws SQLException;
	
	public void updateTogether(TogetherVO together) throws SQLException;
	
	public void deleteTogether(int tno) throws SQLException;
	
	public void increaseViewCnt(int tno) throws SQLException;
	
	public int getSeqNextValue() throws SQLException;
	
	public int getUnqSeqNextValue() throws SQLException;	

	public void deadLineTogether(String tno, String t_state) throws SQLException;

}
