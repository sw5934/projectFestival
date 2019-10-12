package com.spring.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.dto.MemberVO;

public interface MemberService {

//	회원 리스트 가져오기
	public List<MemberVO> getMemberList() throws SQLException;
	
	public MemberVO getMemberByID(String id) throws SQLException;
	public MemberVO getMemberByNickName(String nickName) throws SQLException;
	
//  ID/PW 찾기
	public String getMemberID(String name, String email) throws SQLException;
	
	public MemberVO getMemberPwd(String id, String name, String email) throws SQLException;
	
	public List<MemberVO> getMemberAuthority(String id) throws SQLException;
	
//	회원정보 수정
	public void regist(MemberVO member) throws SQLException;
	
	public void modify(MemberVO member) throws SQLException;
	
	public void remove(String id) throws SQLException;
	
//	로그인
	public int loginFail(String id) throws SQLException;
	public String loginSuccess(String id) throws SQLException;
	public int getLoginFailCnt(String id) throws SQLException;
	public int getLoginFailRecord(String id) throws SQLException;
	public void setLoginFailRecord(String id) throws SQLException;
	public void setNewPassword(String id,String pwd) throws SQLException;
	public void removeLoginRecord(String id) throws SQLException;
	
}
