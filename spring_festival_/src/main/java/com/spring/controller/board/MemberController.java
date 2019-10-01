package com.spring.controller.board;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.dto.MemberVO;
import com.spring.service.MemberService;


@Controller
@RequestMapping("/member")
public class MemberController {
	@Resource(name="memberService")
	private MemberService memberService;
	
	
	// 회원 본인의 마이페이지 회원정보 조회 // 닉네임 , 연락처, 이메일, 생년월일, 성별, 지역, 여행타입, 정보공개여부
	@RequestMapping(value="/memInfo", method=RequestMethod.POST)
	public String memInfo(Model model,
							@RequestParam("id") String id,
							@RequestParam("pwd") String pwd,
							HttpServletRequest request,
							HttpServletResponse response) throws SQLException {
		response.setContentType("text/html;charset=utf-8");


		System.out.println("MemberController.memInfo(),  idweqrqwe="+id);
		System.out.println("MemberController.memInfo(),   pwd="+pwd);

		
		MemberVO memberVO = memberService.getMember(id);
		System.out.println("MemberController.memInfo(),   memberVO = " + memberVO);
		
		
		Map<String, Object> dataMap=new HashMap<String, Object>();
		

		dataMap.put("myInfo", memberVO);
		model.addAttribute("dataMap",dataMap);
		
		
		return "/memInfo/memInfo";
	}
}
