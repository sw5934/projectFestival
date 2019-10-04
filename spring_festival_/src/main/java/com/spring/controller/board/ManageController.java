package com.spring.controller.board;

import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.service.ManageService;

@Controller
@RequestMapping("/manage")
public class ManageController {
	

	@ModelAttribute("category")
	public String category() throws Exception{
		return "manage";		
	}
	
	@Autowired
	private ManageService manageService;
	
	private static final Logger logger=	LoggerFactory.getLogger(ManageController.class);
	

	@RequestMapping("/reportList")
	public Map<String,Object> selectReportList(SearchCriteria cri) throws SQLException {
		cri.setPerPageNum(15);
		Map<String,Object> dataMap = manageService.selectReportList(cri);
		return dataMap;
	}

	@RequestMapping("/doReport")
	public void doReport(String reporter, int unq_id) throws SQLException {
		manageService.doReport(reporter, unq_id);
		
	}

	@RequestMapping("/deleteReport")
	public void deleteReport(int unq_id) throws SQLException {
		manageService.deleteReport(unq_id);
		
	}
	

	@RequestMapping("/reportCommentList")
	public Map<String,Object> selectReportCommentList(SearchCriteria cri) throws SQLException {
		cri.setPerPageNum(15);
		Map<String,Object> dataMap = manageService.selectReportCommentList(cri);
		return dataMap;
	}

	@RequestMapping("/doReportComment")
	public void doReportComment(String reporter, int unq_id) throws SQLException {
		manageService.doReport(reporter, unq_id);
		
	}

	@RequestMapping("/deleteReportComment")
	public void deleteReportComment(int c_no) throws SQLException {
		manageService.deleteReport(c_no);
		
	}
	
	
}
