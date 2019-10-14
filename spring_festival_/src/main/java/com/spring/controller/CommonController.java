package com.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.dto.MemberVO;
import com.spring.service.MailService;
import com.spring.service.MemberService;

@Controller
@RequestMapping("/")
public class CommonController {
		
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
    private MailService mailService;
		
	@RequestMapping("/main.htm")
	public void main() {
	}
	
	// 권한 없는 회원이 접근 시 에러페이지
	@RequestMapping("/error")
	public void error(HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('접근할 수 없습니다.');");
		out.println("history.go(-1)();");
		out.println("</script>");
	}
	
	
//	젝슨 데이터 바인드 중요!! 자파일 추가
//	ResponseEntity는 json한테 에러를 보낼수 있다. 개발자코드 헤더 제너럴 스테이터스 코드 200(성공) 로 확인

	@RequestMapping(value = "/signUp", method = RequestMethod.GET) 
	public String signUpGET() throws Exception {
		
		String url = "common/signUp";
		
		return url;
				
	}
	
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String signUpPost(MemberVO member,String birthM,String birthD,String birthY, String mailAddress) throws Exception {
		System.out.println(mailAddress);
		
		member.setEmail(mailAddress);
		
		if(birthM.length()<2)
			birthM = "0"+birthM;
		if(birthD.length()<2)
			birthD = "0"+birthD;

		System.out.println("!!!!!!!!!!!!!!!"+member.toString());
		System.out.println(birthY+birthM+birthD);
		int birth = Integer.parseInt(birthY+birthM+birthD);
		member.setBirth(birth);
		memberService.regist(member);
		
		return "review/list";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	public String loginGET() throws Exception {
		
		String url = "common/login";
		
		return url;
				
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Map<String,String>> loginPOST(@RequestBody Map<String,String> ajaxData) throws Exception {
		
		
		ResponseEntity<Map<String,String>> entity = null;
		Map<String,String> data = new HashMap<String,String>();
		String id = ajaxData.get("id");
		String pwd = ajaxData.get("pwd");
		data.put("id", id);
		data.put("pwd", pwd);
		String loginStatus;
		String url = "login";
		MemberVO member = memberService.getMemberByID(id);
		
		if(member != null) {							//ID존재
			
			int recordCount = memberService.getLoginFailRecord(id);
			if(recordCount!=1) {
				if(recordCount>1)
					memberService.removeLoginRecord(id);
				memberService.setLoginFailRecord(id);
			};
			
			if(pwd.equals(member.getPwd())) {			//로그인 성공
				String temporary = memberService.loginSuccess(id); //임시비밀번호 발송여부
				loginStatus = "loginSuccess";;		
				if(temporary.equals("no")) {
					url = "loginPost";
				}else{
					url = "newPassword";
				}
				entity = new ResponseEntity<Map<String,String>>(data, HttpStatus.OK);
			} else {						
				if(memberService.loginFail(id) == 5) {						//횟수가 5가 되면 임시메일 발송
					member.setPwd(UUID.randomUUID().toString().replace("-", "").toUpperCase().substring(0, 6));
					mailService.sendMail(member.getEmail(),"임시 비밀번호","임시 비밀번호 : " + member.getPwd());
					memberService.modify(member);
					data.put("sendMail", "yes");
				}else {									//이미 임시메일을 발송 한 이후 처리
					data.put("sendMail", "no");}
				
				loginStatus = "loginFail";
				
				entity = new ResponseEntity<Map<String,String>>(data, HttpStatus.OK);
			}
			
		} else {	//ID가 존재하지 않을 경우
			loginStatus = "IDNotFound";
			entity = new ResponseEntity<Map<String,String>>(data, HttpStatus.OK);
		}
		
		data.put("status", loginStatus);
		data.put("url", url);
		return entity;
	}
	
	@RequestMapping(value = "/findID", method = RequestMethod.GET) 
	public String findIDGET() throws Exception {
		
		String url = "common/findID";
		
		return url;
				
	}
	@RequestMapping(value = "/findID.do", method = RequestMethod.POST)
	public ResponseEntity<Map<String,String>> findIdCheck(String name, String email) throws Exception {
		ResponseEntity<Map<String,String>> entity = null;
		Map<String,String> data = new HashMap<String,String>();
		
		String id = memberService.getMemberID(name, email);
		
		if(memberService.getMemberID(name, email)!=null) {
			data.put("data", "아이디 찾기 성공!");
			data.put("id", id);
			entity = new ResponseEntity<Map<String,String>>(data, HttpStatus.OK);
			}
		else {
			data.put("data", "아이디 찾기 실패!");
			data.put("id", id);
			entity = new ResponseEntity<Map<String,String>>(data, HttpStatus.INTERNAL_SERVER_ERROR);}
		return entity;
	}
	
	@RequestMapping(value = "/findPassword", method = RequestMethod.GET) 
	public String findPasswordGET() throws Exception {
		
		String url = "common/findPassword";
		
		return url;
	}
	
	@RequestMapping(value = "/findPassword.do", method = RequestMethod.POST)
	public ResponseEntity<Map<String,String>> findPasswordCheck(@RequestBody Map<String, String> params, HttpServletRequest request) throws Exception {
		String id = params.get("id");
		String name = params.get("name");
		String email = params.get("email");

		ResponseEntity<Map<String,String>> entity = null;
		Map<String,String> data = new HashMap<String,String>();
		
		
		
		if(memberService.getMemberPwd(id, name, email)!=null) {
			MemberVO member = memberService.getMemberPwd(id, name, email);
			System.out.println("member:"+member);
			
			String pwd = member.getPwd();
			member.setFailCnt(6);
			memberService.modify(member);
			System.out.println("@@@@@@@@@@@@@@"+member);
			
			data.put("data", "비밀번호 찾기 성공!");
			data.put("pwd", pwd);
			mailService.sendMail(email,"임시 비밀번호","임시 비밀번호 : " + pwd);
			entity = new ResponseEntity<Map<String,String>>(data, HttpStatus.OK);
			}
		else {
			data.put("data", "비밀번호 찾기 실패!");
			data.put("pwd", "pwd");
			entity = new ResponseEntity<Map<String,String>>(data, HttpStatus.INTERNAL_SERVER_ERROR);}
		return entity;
	}

	@RequestMapping(value="/newPassword",method=RequestMethod.POST)
	public String newPasswordGET(Model model,String id) throws SQLException{
		model.addAttribute("id",id);
		return "common/newPassword";
	}
	
	@RequestMapping(value="/newPasswordUpdate",method=RequestMethod.POST)
	public String newPasswordPOST(String id, String pwd) throws SQLException {
		memberService.setNewPassword(id, pwd);
		return "redirect:login";
	}
	
	
	@RequestMapping("/memInfoCall")
	public String memInfoCall() {
		System.out.println("CommonController.memInfoCall(), memInfoCall.jsp를 리턴.");
		return "/memInfo/memInfoCall";
	}
	
}
