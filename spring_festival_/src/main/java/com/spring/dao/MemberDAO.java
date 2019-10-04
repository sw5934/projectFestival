package com.spring.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.spring.dto.MemberVO;

public interface MemberDAO {
	
	public List<MemberVO> selectMemberList() throws SQLException;
	
	public MemberVO selectMemberByID(String id) throws SQLException;
	
	public MemberVO selectMemberByNickName(String nickName) throws SQLException;
	
	public int selectMemberByEmail(String email) throws SQLException;
	
	public String findMemberID(String name, String email) throws SQLException;
	
	public MemberVO findMemberPwd(String id, String name, String email) throws SQLException;
	
	public List<Integer> selectMemberAuthority(String id) throws SQLException;
	
	public int loginFailSelect(String id) throws SQLException;
	
	public void setLoginFailRecord(String id) throws SQLException;
	
	public void loginFailUpdate(String id) throws SQLException;
	
	public void loginSuccessUpdate(String id) throws SQLException;
	
	public int loginFailRecord(String id) throws SQLException;
	
	public void insertMember(MemberVO member) throws SQLException;
	
	public void updateMember(MemberVO member) throws SQLException;
	
	public void deleteMember(String id) throws SQLException;
	
	public void newPassword(String id,String pwd) throws SQLException;
}
