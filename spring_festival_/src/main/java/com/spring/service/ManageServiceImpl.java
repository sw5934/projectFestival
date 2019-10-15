package com.spring.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.controller.board.PageMaker;
import com.spring.controller.board.SearchCriteria;
import com.spring.dao.MemberDAO;
import com.spring.dao.ReportDAO;
import com.spring.dto.MemberVO;
import com.spring.dto.ReportVO;
import com.spring.dto.Report_CVO;

public class ManageServiceImpl implements ManageService{

	private ReportDAO reportDAO;
	public void setReportDAO(ReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}
	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public Map<String,Object> selectReportList(SearchCriteria cri) throws SQLException {
		List<ReportVO> reportList = reportDAO.selectReportList(cri);

		for(ReportVO report:reportList) {
			report.setNickName(memberDAO.selectMemberByID(report.getId()).getNickName());
			report.setReporterNick(memberDAO.selectMemberByID(report.getReporter()).getNickName());
		}
		
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(reportDAO.selectReportListCount(cri));
		System.out.println("totalCount="+pageMaker.getTotalCount());
		Map<String,Object> dataMap = new HashMap<String,Object>();
		
		dataMap.put("reportList", reportList);
		dataMap.put("pageMaker", pageMaker);
		System.out.println(pageMaker.toString());
		
		return dataMap;
	}

	@Override
	public void doReport(String reporter, int unq_id) throws SQLException {
		String nickName = ((MemberVO)memberDAO.selectMemberByID(reporter)).getNickName();
		reportDAO.doReport(reporter,nickName, unq_id);
		
	}

	@Override
	public void deleteReport(int unq_id) throws SQLException {
		reportDAO.deleteReport(unq_id);
		
	}
	
	@Override
	public Map<String,Object> selectReportCommentList(SearchCriteria cri) throws SQLException {
		List<Report_CVO> reportList = reportDAO.selectReportCommentList(cri);

		for(Report_CVO report:reportList) {
			report.setNickName(memberDAO.selectMemberByNickName(report.getId()).getNickName());
			report.setReporterNick(memberDAO.selectMemberByID(report.getReporter()).getNickName());
		}
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(reportDAO.selectReportCommentListCount(cri));
		Map<String,Object> dataMap = new HashMap<String,Object>();
		
		dataMap.put("reportList", reportList);
		dataMap.put("pageMaker", pageMaker);
		System.out.println(pageMaker.toString());
		
		return dataMap;
	}

	@Override
	public void doReportComment(String reporter, int c_no) throws SQLException {
		String nickName = ((MemberVO)memberDAO.selectMemberByID(reporter)).getNickName();
		reportDAO.doReportComment(reporter,nickName, c_no);
		
	}

	@Override
	public void deleteReportComment(int c_no) throws SQLException {
		reportDAO.deleteReportComment(c_no);
		
	}
}
