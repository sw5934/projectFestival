package com.spring.service;

import java.sql.SQLException;
import java.util.Map;

import com.spring.controller.board.SearchCriteria;

public interface ManageService {


	public Map<String,Object> selectReportList(SearchCriteria cri) throws SQLException;
	
	public void doReport(String reporter, int unq_id) throws SQLException;
	
	public void deleteReport(int unq_id) throws SQLException;

	public Map<String,Object> selectReportCommentList(SearchCriteria cri) throws SQLException;
	
	public void doReportComment(String reporter, int c_no) throws SQLException;
	
	public void deleteReportComment(int c_no) throws SQLException;

}
