package com.spring.service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.spring.dao.MemberDAO;
import com.spring.dto.MemberVO;

public class MemberServiceImpl implements MemberService {

	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	
//	내가 한거	
//	public MemberServiceImpl() {
//		memberDAO = new MemberDAOImpl();
//	}

	@Override
	public List<MemberVO> getMemberList() throws SQLException {
				
		List<MemberVO> memberList = memberDAO.selectMemberList();
		
		for(int i = 0; memberList != null && i<memberList.size(); i++) {
			List<Integer> authority = memberDAO.selectMemberAuthority(memberList.get(i).getId());
			memberList.get(i).setAuthority(authority);
		}
		
		return memberList;
	}

	@Override
	public MemberVO getMemberByID(String id) throws SQLException {
		
		MemberVO member = memberDAO.selectMemberByID(id);
		
		if(member != null) {
			List<Integer> authority = memberDAO.selectMemberAuthority(id);
			member.setAuthority(authority);
		}
		
		return member;
	}
	
	@Override
	public MemberVO getMemberByNickName(String nickName) throws SQLException {
		
		MemberVO member = memberDAO.selectMemberByNickName(nickName);
		
		return member;
	}

	@Override
	public void regist(MemberVO member) throws SQLException {
		
		memberDAO.insertMember(member);

	}

	@Override
	public void modify(MemberVO member) throws SQLException {

		memberDAO.updateMember(member);

	}

	@Override
	public void remove(String id) throws SQLException {
		
		memberDAO.deleteMember(id);

	}


	@Override
	public String getMemberID(String name, String email) throws SQLException {
		
		String id = memberDAO.findMemberID(name, email);
		
		return id;
	}


	@Override
	public MemberVO getMemberPwd(String id, String name, String email) throws SQLException {
		
		MemberVO pwd_member = memberDAO.findMemberPwd(id, name, email);
		String temp_pwd = UUID.randomUUID().toString().replace("-", "").toUpperCase().substring(0, 5);
		
		if(pwd_member != null) {
			pwd_member.setPwd(temp_pwd);
		}
		
		return pwd_member;
	}


	@Override
	public List<MemberVO> getMemberAuthority(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
