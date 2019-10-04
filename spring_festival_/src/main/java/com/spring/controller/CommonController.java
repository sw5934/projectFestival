package com.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
	
	
//	젝슨 데이터 바인드 중요!! 자파일 추가
//	ResponseEntity는 json한테 에러를 보낼수 있다. 개발자코드 헤더 제너럴 스테이터스 코드 200(성공) 로 확인

	@RequestMapping(value = "/signUp", method = RequestMethod.GET) 
	public String signUpGET() throws Exception {
		
		String url = "common/signUp";
		
		return url;
				
	}
	
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String signUpPost(MemberVO member,String birthM,String birthD,String birthY, String mailAddress,String location1, String location2) throws Exception {
		System.out.println(mailAddress);
		
		member.setEmail(mailAddress);
		
		if(birthM.length()<2)
			birthM = "0"+birthM;
		if(birthD.length()<2)
			birthD = "0"+birthD;
		String address = location1 
				+ location2;
		member.setAddress(address);

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
		System.out.println("QQQQQQQQQ" + id + pwd);
		
		MemberVO member = memberService.getMemberByID(id);
		
		System.out.println("@@@@@@@@@@@@@"+member.getFailCnt());
		
		int failCnt = memberService.loginFail(id);
System.out.println("failCnt:"+failCnt);
		if(member != null) {
			if(pwd.equals(member.getPwd())) {	// 로그인 성공
				if(member.getFailCnt() > 5) {
					// 비밀번호 변경창
					
				}
				data.put("status", "loginSuccess");
				data.put("id", id);
				data.put("pwd", pwd);
				data.put("url", "/festival/review/list");
				entity = new ResponseEntity<Map<String,String>>(data, HttpStatus.OK);
			} else {	// 비밀번호 틀림
				if(member.getFailCnt() == 5) {
					member = memberService.getMemberPwd(id, member.getName(), member.getEmail());
					mailService.sendMail(member.getEmail(),"임시 비밀번호","임시 비밀번호 : " + member.getPwd());
					data.put("status", "loginFail");
				} else if(member.getFailCnt() != 5) {
					memberService.loginFail(id);
					data.put("status", "loginFail");
				}
				data.put("status", "else");
				data.put("id", id);
				data.put("pwd", pwd);
				data.put("url", "login");
				/*entity = new ResponseEntity<Map<String,String>>(data, HttpStatus.INTERNAL_SERVER_ERROR);*/
			}
			
		} else {
			System.out.println(member.getFailCnt());
			data.put("status", "~~~~");
			data.put("id", id);
			data.put("pwd", pwd);
			data.put("url", "login");
			entity = new ResponseEntity<Map<String,String>>(data, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		System.out.println(data + "\n\n\n" + entity);
		
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
	
	
	
	@RequestMapping("/memInfoCall")
	public String memInfoCall() {
		System.out.println("CommonController.memInfoCall(), memInfoCall.jsp를 리턴.");
		return "/memInfo/memInfoCall";
	}
	
}
