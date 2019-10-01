package com.spring.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.controller.board.PageMaker;
import com.spring.controller.board.SearchCriteria;
import com.spring.dao.ReportDAO;
import com.spring.dto.ReportVO;
import com.spring.dto.Report_CVO;

public class ManageServiceImpl implements ManageService{

	private ReportDAO reportDAO;
	public void setReportDAO(ReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}

	@Override
	public Map<String,Object> selectReportList(SearchCriteria cri) throws SQLException {
		List<ReportVO> reportList = reportDAO.selectReportList(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(reportDAO.selectReportListCount());
		System.out.println("totalCount="+pageMaker.getTotalCount());
		Map<String,Object> dataMap = new HashMap<String,Object>();
		
		dataMap.put("reportList", reportList);
		dataMap.put("pageMaker", pageMaker);
		System.out.println(pageMaker.toString());
		
		return dataMap;
	}

	@Override
	public void doReport(String reporter, int unq_id) throws SQLException {
		reportDAO.doReport(reporter, unq_id);
		
	}

	@Override
	public void deleteReport(int unq_id) throws SQLException {
		reportDAO.deleteReport(unq_id);
		
	}
	
	@Override
	public Map<String,Object> selectReportCommentList(SearchCriteria cri) throws SQLException {
		List<Report_CVO> reportList = reportDAO.selectReportCommentList(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(reportDAO.selectReportCommentListCount());
		Map<String,Object> dataMap = new HashMap<String,Object>();
		
		dataMap.put("reportList", reportList);
		dataMap.put("pageMaker", pageMaker);
		System.out.println(pageMaker.toString());
		
		return dataMap;
	}

	@Override
	public void doReportComment(String reporter, int c_no) throws SQLException {
		reportDAO.doReportComment(reporter, c_no);
		
	}

	@Override
	public void deleteReportComment(int c_no) throws SQLException {
		reportDAO.deleteReportComment(c_no);
		
	}
}
