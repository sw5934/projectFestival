package com.spring.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.dto.MemberVO;

public interface MemberService {

//	회원 리스트 가져오기
	public List<MemberVO> getMemberList() throws SQLException;
	
	public MemberVO getMember(String id) throws SQLException;
	
//  ID/PW 찾기
	public String getMemberID(String name, String email) throws SQLException;
	
	public List<MemberVO> getMemberPwd(String id, String name, String email) throws SQLException;
	
	public List<MemberVO> getMemberAuthority(String id) throws SQLException;
	
//	회원정보 수정
	public void regist(MemberVO member) throws SQLException;
	
	public void modify(MemberVO member) throws SQLException;
	
	public void remove(String id) throws SQLException;
	
}
