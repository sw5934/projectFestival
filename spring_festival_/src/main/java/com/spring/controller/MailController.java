package com.spring.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dao.MemberDAO;
import com.spring.service.MailService;
import com.spring.service.MemberService;

@RestController
@EnableAsync
@RequestMapping("/signUp")
public class MailController {
    
	
	
	@Autowired
    private MailService mailService;
 
    @RequestMapping(value = "/sendMail.do", method = RequestMethod.POST)
    public ResponseEntity<Map<String,String>> sendSimpleMail(@RequestBody String email) throws Exception{
    	
    	ResponseEntity<Map<String,String>> entity = null;
		Map<String,String> data = new HashMap<String,String>();
    	
    	String code = UUID.randomUUID().toString().replace("-", "").toUpperCase().substring(0, 5);
    	System.out.println("dddddd"+email);
    	int chkemail = mailService.getMemberByEmail(email);
    	
    	System.out.println("chkemail:"+chkemail);
    	
    	if(chkemail == 0) {
//			data.put("data", "사용 가능한 닉네임 입니다.");
    		mailService.sendMail(email,"이메일 인증 코드","이메일 인증 코드 : "+code);
//          mailService.sendPreConfiguredMail("테스트 메일입니다.");
    		System.out.println("메일을 보냈습니다!!");
    		System.out.println(email);
    		
    		data.put("code", code);
    		
			entity = new ResponseEntity<Map<String,String>>(data, HttpStatus.OK);
		} else {
//			data.put("data", "사용할 수 없는 닉네임 입니다.");
			entity = new ResponseEntity<Map<String,String>>(data, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
    	
		System.out.println(entity);
    	
        return entity;
    }
    
}


