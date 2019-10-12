package com.spring.controller.board;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dto.MemberVO;
import com.spring.service.MyPageService;

@Controller
@RequestMapping("/myPage")
public class MyPageController {
	// xml에 설정해놓은 빈들중 <bean id="myPageService">인 빈을 ms에 주입시킨다.
	@Resource(name="myPageService")
	MyPageService ms;
	
	
	@RequestMapping("/review")
	public String myReviewList(Model model, Second_Criteria cri,
			HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		cri.setStr(loginUser.getId());
		cri.setPerPageNum(10);
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap = ms.myReviewList(cri);
		// dataMap = { "myReviewList", "pageMaker" }
		
		
		model.addAttribute("dataMap", dataMap);
		
		return "/myPage/review";
	}
	
	@RequestMapping("/together")
	public ModelAndView myTogetherList(ModelAndView mnv, Second_Criteria cri,
			HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		cri.setStr(loginUser.getId());
		cri.setPerPageNum(10);
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap = ms.myTogetherList(cri);
		// dataMap = { "myTogetherList", "pageMaer" }
		
		System.out.println(dataMap);
		
		
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName("/myPage/together");
		
		
		return mnv;
	}
	

	@RequestMapping("/comments")
	public String commentsBoard(Model model, Second_Criteria cri,
			HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		cri.setStr(loginUser.getId());
		cri.setPerPageNum(10);
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap = ms.commentsBoard(cri);
		// dataMap = { "commentsList", "pageMaker" }
		
		model.addAttribute("dataMap", dataMap);
		
		return "/myPage/comments";
	}
	
	@RequestMapping("/holding")
	public String festivalHolding(Model model, Second_Criteria cri,
					HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		cri.setStr(loginUser.getId());
		cri.setPerPageNum(2);
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap = ms.holdingList(cri); 
		// dataMap = { "holdingList", "pageMaker" }

		model.addAttribute("dataMap", dataMap);
		System.out.println("컨트롤러 : " + dataMap);

		
		return "/myPage/holding";
	}
	
	
	
	@RequestMapping("/authoritySet")
	public String authoritySet(Model model, Second_Criteria cri,
							HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		cri.setPerPageNum(5);
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap = ms.authSetting(cri);
		
		model.addAttribute("dataMap", dataMap);
		
		System.out.println("컨트롤러 : " + dataMap);
		
		return "/myPage/auth";
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
