package com.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.controller.board.ReviewController;
import com.spring.service.MemberService;

@RestController
@RequestMapping("/signUp")
public class SignUpController {

	@Autowired
	private MemberService memberService;
	private static final Logger logger=	LoggerFactory.getLogger(SignUpController.class);

	@RequestMapping(value = "/idCheck.do", method = RequestMethod.POST)
	public ResponseEntity<Map<String,String>> signUpIdCheck(@RequestBody String id) throws Exception {
		ResponseEntity<Map<String,String>> entity = null;
		Map<String,String> data = new HashMap<String,String>();
		
		System.out.println("ddddddddddd"+id);
		
		if(memberService.getMemberByID(id)==null) {
			data.put("data", "사용 가능한 아이디 입니다.");
			data.put("id", id);
			entity = new ResponseEntity<Map<String,String>>(data, HttpStatus.OK);}
		else {
			data.put("data", "사용할 수 없는 아이디 입니다.");
			entity = new ResponseEntity<Map<String,String>>(data, HttpStatus.INTERNAL_SERVER_ERROR);}
		return entity;
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
