package com.spring.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.spring.dto.MemberVO;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		ApplicationContext ctx = new GenericXmlApplicationContext("classpath:com/spring/context/root-context.xml");
		
		SqlSession session=ctx.getBean("sqlSession",SqlSession.class);
		
		
		User user = (User)authentication.getDetails();
		
		MemberVO loginUser = user.getMemberVO();
		
		HttpSession userSession = request.getSession();
		
		userSession.setAttribute("loginUser", loginUser);
		
		System.out.println("session:"+session);
		
		int checkBlackList = session.selectOne("Member.checkBlackList",loginUser.getId());
		if(checkBlackList!=0) {
			Date now = new Date();
			Date endDate = session.selectOne("Member.checkBlackListEndDate",loginUser.getId());
			if(endDate.compareTo(now)==-1) {
				
				session.update("Member.deleteBlackList", loginUser.getId());
				session.update("Member.deleteAuthority", loginUser.getId());
				session.update("Member.insertAuthority", loginUser.getId());
			}
		}
		
		
		
		
		// 자동 로그아웃되도록 설정
		userSession.setMaxInactiveInterval(30*60);
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

	
}
