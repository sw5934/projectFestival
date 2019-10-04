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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dto.MemberVO;
import com.spring.service.MyPageService;

@Controller
@RequestMapping("/MyPage")
public class MyPageController {
	@Resource(name="myPageService")
	private MyPageService bs;
	
	// 마이페이지 -> '후기게시판'에 작성한 나의 글 리스트업
	@RequestMapping("/review")
	public ModelAndView myReviewList(ModelAndView modelnView, Second_Criteria cri,
			HttpServletRequest request,	HttpServletResponse response) throws SQLException {
		System.out.println("MyPageController.reviewBoard() 시작");
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		cri.setStr(loginUser.getId());
		cri.setPerPageNum(10);
		

		
		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap = bs.myReviewList(cri);   // dataMap { myReviewList, 
										  //           pageMaker { cri.getStr() } 
										  //		 }
		
		
		

		modelnView.addObject("dataMap",dataMap);		
		modelnView.setViewName("myPage/review");
		
		System.out.println("MyPageController.reviewBoard() 종료");
		return modelnView;
	}
	
	
	
	
	
	// 마이페이지 -> '같이가요'게시판에 작성한 나의 글 리스트업
	@RequestMapping("/together")
	public Model togetherGo(Second_Criteria cri, Model model,
				HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		cri.setStr(loginUser.getId());
		cri.setPerPageNum(10);
		
		
		
		
		
		return model;
	}
	
}
