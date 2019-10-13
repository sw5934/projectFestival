package com.spring.controller.board;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.dto.MemberVO;
import com.spring.service.ManageService;
import com.spring.service.MyPageService;

@Controller
@RequestMapping("/manage")
public class ManageController {
	

	@ModelAttribute("category")
	public String category() throws Exception{
		return "manage";		
	}
	
	@Autowired
	private ManageService manageService;
	
	@Resource(name="myPageService")
	MyPageService ms;
	
	private static final Logger logger=	LoggerFactory.getLogger(ManageController.class);
	

	@RequestMapping("/reportList")
	public Map<String,Object> selectReportList(SearchCriteria cri) throws SQLException {

		if(cri.getKeyword().equals(""))
			cri.setSearchType("nickname");
		
		cri.setPerPageNum(15);
		Map<String,Object> dataMap = manageService.selectReportList(cri);
		dataMap.put("cri", cri);
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
		dataMap.put("cri", cri);
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
	
	

	
	@RequestMapping("/authoritySet")
	public String authoritySet(Model model, Second_Criteria cri,
							HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		cri.setPerPageNum(15);
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		dataMap = ms.authSetting(cri);
		
		model.addAttribute("dataMap", dataMap);
		
		System.out.println("컨트롤러 : " + dataMap);
		
		return "/manage/auth";
	}
	
	
	
	@RequestMapping("/authUpdate")
	public ResponseEntity<String> authUpdate(@RequestBody Map<String, String> strMap) 
			throws SQLException {
		System.out.println("MyPageController.authUpdate() 실행");
		System.out.println(strMap);
		
		if(strMap.get("auth").equals("관리자")) {
			strMap.put("auNo", "4");
		} else if(strMap.get("auth").equals("개최자")) {
			strMap.put("auNo", "3");
		} else if(strMap.get("auth").equals("일반회원")) {
			strMap.put("auNo", "2");
		} else if(strMap.get("auth").equals("제재회원")) {
			strMap.put("auNo", "1");
		} else if(strMap.get("auth").equals("비회원")) {
			strMap.put("auNo", "0");
		}
		
		
		ms.authUpdate(strMap);
		
		
		ResponseEntity<String> responseEntity = null;
		
		
		
		try {
			responseEntity = new ResponseEntity(HttpStatus.OK);			
		} catch (Exception e) {
			responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return responseEntity;
	}
}
