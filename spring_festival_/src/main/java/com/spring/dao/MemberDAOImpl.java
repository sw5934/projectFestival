package com.spring.dao;

import java.sql.SQLException;
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
	public String findMemberID(String name, String email) throws SQLException {
		
		MemberVO member = new MemberVO();
		
		member.setName(name);
		member.setEmail(email);
		
		String id = session.selectOne("Member.findMemberID", member);
		
		return id;
	}
	
	@Override
	public List<MemberVO> findMemberPwd(String id, String name, String email) throws SQLException {
		
		MemberVO member = new MemberVO();
		
		member.setId(id);
		member.setName(name);
		member.setEmail(email);
		
		List<MemberVO> pwd_member = session.selectList("Member.findMemberPwd", member);
		
		return pwd_member;
	}

	@Override
	public void insertMember(MemberVO member) throws SQLException {

		session.update("Member.insertMember",member);

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
	public MemberVO loginFailSelect(String id) throws SQLException {
		MemberVO member = session.selectOne("Member.loginFailSelect", id);
		
		return member;
	}

	@Override
	public void loginFailInsert(MemberVO member) throws SQLException {
		session.update("Member.loginFailInsert", member);
		
	}

	@Override
	public void loginFailUpdate(MemberVO member) throws SQLException {
		session.update("Member.loginFailUpdate", member);
		
	}

	@Override
	public void loginSuccessUpdate(String id) throws SQLException {
		session.update("Member.loginSuccessUpdate", id);
		
	}

}
