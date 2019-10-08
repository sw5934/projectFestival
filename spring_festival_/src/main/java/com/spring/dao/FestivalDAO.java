package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.FestivalVO;

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
}
