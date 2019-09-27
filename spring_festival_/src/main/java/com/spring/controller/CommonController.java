package com.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.dao.MemberDAO;

@Controller
@RequestMapping("/")
public class CommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);	
	
	@Autowired
	private MemberDAO memberDAO;
		
	@RequestMapping("/main.htm")
	public void main() {
		logger.info("request url : /main");
	}
	
	
//	젝슨 데이터 바인드 중요!! 자파일 추가
//	ResponseEntity는 json한테 에러를 보낼수 있다. 개발자코드 헤더 제너럴 스테이터스 코드 200(성공) 로 확인

	@RequestMapping(value = "/signUp", method = RequestMethod.GET) 
	public String signUpGET() throws Exception {
		
		String url = "common/signUp";
		
		return url;
				
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	public String loginGET() throws Exception {
		
		String url = "common/login";
		
		return url;
				
	}
	
	@RequestMapping("/idCheck.do")
	@ResponseBody
	public Map<Object, Object> idCheck(@RequestBody String id) {
		
		int count = 0;
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		count = signUpService.idCheck(id);
		map.put("cnt", count);
		
		return map;
	}
	
//	리스폰스나 리퀘스트가 없으면 화면을 결정할수있는 인자가 없다 그래서 메소드에서 리턴되는 녀석을
//	뷰리졸버에게 넘긴다.
//	밑에는 리스폰스가 있어서 뷰리졸버에게 반환된 스트링을 넘기지않는다. 
	
	/*@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void loginPOST(String id, String pwd, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("request url : /login (post)");
		logger.info("id : " + id + ", pwd : " + pwd);
		
		String message = "";
		String url = "";
		
		MemberVO member = memberDAO.selectMemberByID(id);
		
		
		if(member != null) {	//아이디가 있는경우
			if(pwd.equals(member.getPwd())) {	// 로그인 성공
				
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", member);
				
				message = "로그인 성공했습니다.";
				url = "main.htm";
				
			} else { //패스워드 불일치
				message = "패스워드가 틀렸습니다.";
				url = "login";
			}
			
		} else { //아이디가 없는 경우
			message = "아이디가 존재하지 않습니다.";
			url = "login";
		}
		
		
//		response.getWriter().println(new Scanner(System.in).nextLine());
		
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		out.println("alert('"+message+"');");
		out.println("self.location='"+url+"';");
		out.println("</script>");
		
				
		
	}*/
	
	
}
