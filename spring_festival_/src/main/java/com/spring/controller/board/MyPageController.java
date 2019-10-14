package com.spring.controller.board;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@ModelAttribute("category")
	public String category() throws Exception{
		return "myPage";		
	}

	@ModelAttribute("view")
	public String view() throws Exception{
		return "작성 글/댓글";		
	}
	// 마이페이지 -> '후기게시판'에 작성한 나의 글 리스트업
	
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
		cri.setPerPageNum(4);
		
		Map<String, Object> dataMap = ms.holdingList(cri); 
		// dataMap = { "holdingList", "pageMaker" }

		model.addAttribute("dataMap", dataMap);

		
		return "/myPage/holding";
	}
}
