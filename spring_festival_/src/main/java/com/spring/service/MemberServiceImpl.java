package com.spring.service;

import java.sql.SQLException;
import java.util.List;

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
	public MemberVO getMember(String id) throws SQLException {
		
		MemberVO member = memberDAO.selectMemberByID(id);
		
		if(member != null) {
			List<Integer> authority = memberDAO.selectMemberAuthority(id);
			member.setAuthority(authority);
		}
		
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
	public List<MemberVO> getMemberPwd(String id, String name, String email) throws SQLException {
		
		List<MemberVO> pwd_member = memberDAO.findMemberPwd(id, name, email);
		
		if(pwd_member != null) {
			
		}
		
		return pwd_member;
	}


	@Override
	public List<MemberVO> getMemberAuthority(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
