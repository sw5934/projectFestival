package com.spring.controller.board;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.dto.MemberVO;
import com.spring.service.MemberService;


@Controller
@RequestMapping("/member")
public class MemberController {
	@Resource(name="memberService")
	private MemberService memberService;
	

	@ModelAttribute("category")
	public String category() throws Exception{
		return "member";		
	}

	@ModelAttribute("view")
	public String view() throws Exception{
		return "프로필";		
	}
	
	// 회원 본인의 마이페이지 회원정보 조회 // 닉네임 , 연락처, 이메일, 생년월일, 성별, 지역, 여행타입, 정보공개여부
	@RequestMapping("/myInfo")
	public String myInfo(Model model, String id,HttpServletRequest request) throws SQLException {

		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if(loginUser.getId().equals(id))
		{	
			String birth = loginUser.getBirth()+"";
			String year = birth.substring(0,4);
			String month = birth.substring(4,6);
			String date = birth.substring(6,8);
			Map<String,String> birthData = new HashMap<String,String>();
			birthData.put("year", year);
			birthData.put("month", month);
			birthData.put("date", date);
			model.addAttribute("loginUser",loginUser); 
			model.addAttribute("birthData",birthData); 
			return "/memInfo/myInfo";
		}
		
		else
			return "redirect:main.htm";
	}
}
