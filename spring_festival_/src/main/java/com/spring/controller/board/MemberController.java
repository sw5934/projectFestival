package com.spring.controller.board;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.dto.MemberVO;
import com.spring.service.FollowService;
import com.spring.service.MemberService;
import com.spring.service.MyPageService;


@Controller
@RequestMapping("/member")
public class MemberController {
	@Resource(name="memberService")
	private MemberService memberService;
	
	@Autowired
	private FollowService followService;
	

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
	public String myInfo(Model model,String id,HttpServletRequest request) throws SQLException {

		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if(loginUser.getId().equals(id)) {
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
			return "/member/myInfo";
		}
		
		else
			return "redirect:main.htm";
	}
	

	@RequestMapping(value="/memInfo", method=RequestMethod.GET)
	public String memInfo(Model model,String nick,HttpServletRequest request) throws SQLException {
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		System.out.println(nick);
		
		MemberVO member = memberService.getMemberByNickName(nick);
		
		System.out.println(member.toString());
		
		System.out.println("loginUser.getId()="+loginUser.getId());

		
		
		
		
		if(followService.checkFollow(loginUser.getId(), member.getId())==0) {
			model.addAttribute("followRecord", 0);
		}else {
			model.addAttribute("followRecord", 1);
		}
		
	
			String birth = member.getBirth()+"";
			String year = birth.substring(0,4);
			String month = birth.substring(4,6);
			String date = birth.substring(6,8);
			Map<String,String> birthData = new HashMap<String,String>();
			birthData.put("year", year);
			birthData.put("month", month);
			birthData.put("date", date);
			
			model.addAttribute("otherPage",123);
			model.addAttribute("member",member); 
			model.addAttribute("birthData",birthData);
			return "/member/memInfo";
	}
	

	@RequestMapping(value="/myInfoPwdConfirm",method=RequestMethod.GET)
	public void myInfoPwdConfirmGET() throws SQLException {
	}
	@RequestMapping(value="/myInfoPwdConfirm",method=RequestMethod.POST)
	public String myInfoPwdConfirmPOST(HttpServletRequest request,String pwd) throws SQLException {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		String url = "redirect:/member/myInfo?id="+loginUser.getId();
		
		if(pwd.equals(loginUser.getPwd())) {
			url = "redirect:/member/myInfoModify?id="+loginUser.getId();
		}
		
		
		return url;
	}
	
	@RequestMapping(value="/myInfoModify", method=RequestMethod.GET)
	public String myInfoModifyGET(Model model, String id, HttpServletRequest request) throws SQLException {
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
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
		return "/member/myInfoModify";
		
	}
	
	@RequestMapping(value="/myInfoModify", method=RequestMethod.POST)
	public String myInfoModifyPOST(Model model, MemberVO member, HttpServletRequest request) throws SQLException {
		
		memberService.modify(member);
		
		MemberVO loginUser = memberService.getMemberByID(member.getId());
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", loginUser);
		
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
		
		return "/member/myInfo";
	}
	
	@RequestMapping(value = "/nickCheck.do", method = RequestMethod.POST)
	public ResponseEntity<Map<String,String>> signUpNickCheck(@RequestBody String nickName) throws Exception {
		ResponseEntity<Map<String,String>> entity = null;
		Map<String,String> data = new HashMap<String,String>();
		
		if(memberService.getMemberByNickName(nickName)==null) {
			data.put("data", "사용 가능한 닉네임 입니다.");
			entity = new ResponseEntity<Map<String,String>>(data, HttpStatus.OK);}
		else {
			data.put("data", "사용할 수 없는 닉네임 입니다.");
			entity = new ResponseEntity<Map<String,String>>(data, HttpStatus.INTERNAL_SERVER_ERROR);}
		System.out.println(entity);
		return entity;
	}
	
}
