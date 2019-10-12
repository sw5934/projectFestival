package com.spring.controller.board;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.dto.MemberVO;
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

		if(cri.getKeyword().equals(""))
			cri.setSearchType("nickname");
		
		cri.setPerPageNum(15);
		Map<String,Object> dataMap = manageService.selectReportList(cri);
		return dataMap;
	}

	@RequestMapping("/deleteReport")
	public void deleteReport(int unq_id) throws SQLException {
		manageService.deleteReport(unq_id);
		
	}
	

	@RequestMapping("/reportCommentList")
	public Map<String,Object> selectReportCommentList(SearchCriteria cri) throws SQLException {
		

		if(cri.getKeyword().equals(""))
			cri.setSearchType("nickname");
		
		
		
		cri.setPerPageNum(15);
		Map<String,Object> dataMap = manageService.selectReportCommentList(cri);
		return dataMap;
	}


	@RequestMapping("/deleteReportComment")
	public void deleteReportComment(int c_no) throws SQLException {
		manageService.deleteReport(c_no);
		
	}

	@RequestMapping("/doReport")
	public String doReport(HttpServletResponse response, HttpServletRequest request, int unq_id, int no,String page, String listSort, String originCategory) throws Exception {
		HttpSession session = request.getSession();
		String reporter = ((MemberVO)session.getAttribute("loginUser")).getId();
		manageService.doReport(reporter, unq_id);

		String cat = "fno";
		if(originCategory.equals("review"))
			cat = "rno";
		else if(originCategory.equals("together"))
			cat = "tno";
		
		return "redirect:/"+originCategory+"/detail?"+cat+"="+no+"&page="+page+"&listSort="+listSort;
	}

	@RequestMapping("/doReportComment")
	public String doReportComment(HttpServletResponse response, HttpServletRequest request, int c_no, int no,String page, String listSort, String originCategory) throws Exception {
		HttpSession session = request.getSession();
		String reporter = ((MemberVO)session.getAttribute("loginUser")).getId();
		manageService.doReportComment(reporter, c_no);

		String cat = "fno";
		if(originCategory.equals("review"))
			cat = "rno";
		else if(originCategory.equals("together"))
			cat = "tno";
		
		return "redirect:/"+originCategory+"/detail?"+cat+"="+no+"&page="+page+"&listSort="+listSort;
	}
}
