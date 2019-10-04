package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.ReportVO;
import com.spring.dto.Report_CVO;

public interface ReportDAO {
	public List<ReportVO> selectReportList(SearchCriteria cri) throws SQLException;

	public int selectReportListCount() throws SQLException;
	
	public void doReport(String reporter, int unq_id) throws SQLException;
	
	public void deleteReport(int unq_id) throws SQLException;

	public List<Report_CVO> selectReportCommentList(SearchCriteria cri) throws SQLException;

	public int selectReportCommentListCount() throws SQLException;
	
	public void doReportComment(String reporter, int c_no) throws SQLException;
	
	public void deleteReportComment(int c_no) throws SQLException;


}
