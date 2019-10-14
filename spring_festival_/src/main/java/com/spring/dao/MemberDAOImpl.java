package com.spring.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.spring.dto.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	
	private SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session=session;
	}

	@Override		//메소드하나당 1개의 쿼리만 사용하는것을 권장한다.
	public List<MemberVO> selectMemberList() throws SQLException {

		List<MemberVO> memberList = session.selectList("Member.selectMemberList",null);
		
		return memberList;
	}

	@Override
	public MemberVO selectMemberByID(String id) throws SQLException {
		
		MemberVO member = session.selectOne("Member.selectMemberByID", id);
		
		return member;
	}
	
	@Override
	public MemberVO selectMemberByNickName(String nickName) throws SQLException {

		MemberVO member = session.selectOne("Member.selectMemberByNickName", nickName);
		
		return member;
	}
	
	@Override
	public int selectMemberByEmail(String email) throws SQLException {
		
		int countemail = session.selectOne("Member.selectMemberByEmail", email);
		
		return countemail;
	}
	
	@Override
	public String findMemberID(String name, String email) throws SQLException {
		
		MemberVO member = new MemberVO();
		
		member.setName(name);
		member.setEmail(email);
		
		String id = session.selectOne("Member.findMemberID", member);
		
		return id;
	}
	
	@Override
	public MemberVO findMemberPwd(String id, String name, String email) throws SQLException {
		
		MemberVO member = new MemberVO();
		
		member.setId(id);
		member.setName(name);
		member.setEmail(email);
		
		MemberVO pwd_member = session.selectOne("Member.findMemberPwd", member);
		
		return pwd_member;
	}

	@Override
	public void insertMember(MemberVO member) throws SQLException {

		session.update("Member.insertMember",member);
		session.update("Member.insertAuthority",member);

	}

	@Override
	public void updateMember(MemberVO member) throws SQLException {
		session.update("Member.updateMember",member);

	}

	@Override
	public void deleteMember(String id) throws SQLException {
		session.update("Member.deleteMember",id);

	}

	@Override
	public List<Integer> selectMemberAuthority(String id) throws SQLException {
		List<Integer> authority = session.selectList("Member.selectMemberAuthority", id);
		
		return authority;
	}

	@Override
	public int loginFailSelect(String id) throws SQLException {
		int failCount = session.selectOne("Member.loginFailSelect", id);
		
		return failCount;
	}

	@Override
	public void setLoginFailRecord(String id) throws SQLException {
		session.update("Member.setLoginFailRecord", id);
		
	}

	@Override
	public void loginFailUpdate(String id) throws SQLException {
		session.update("Member.loginFailUpdate", id);
		
	}

	@Override
	public void loginSuccessUpdate(String id) throws SQLException {
		session.update("Member.loginSuccessUpdate", id);
		
	}
	
	@Override
	public int loginFailRecord(String id) throws SQLException{
		return session.selectOne("Member.loginFailRecord",id);
	}
	
	public void newPassword(String id,String pwd) throws SQLException{
		Map<String,String> data = new HashMap<String,String>();
		data.put("id", id);
		data.put("pwd", pwd);
		session.update("Member.newPassword",data);
	}

}
