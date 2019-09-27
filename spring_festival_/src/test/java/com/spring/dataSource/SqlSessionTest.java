package com.spring.dataSource;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dto.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/spring/context/dataSource-context.xml")
@TransactionConfiguration(transactionManager="transactionManager")
@Transactional
public class SqlSessionTest {

	@Autowired
	private SqlSession session;
	
	@Test
	public void testSelectMemberList() throws Exception{
		List<MemberVO> memberList = session.selectList("Member.selectMemberList", null);
		
		Assert.assertThat(memberList.size(), Matchers.greaterThan(0));
		
	}
	
	@Test
	public void testSelectMemberByID() throws Exception{
		
		String id = "mimi";
		
		MemberVO member = session.selectOne("Member.selectMemberByID", id);
		
		Assert.assertEquals(id,  member.getId());
		
	}
	
	@Test
	public void testInsertMember() throws Exception{
		
		MemberVO member = new MemberVO();
		
		member.setId("mama");
		member.setPwd("mama");
		member.setEmail("mama@mama.com");
		member.setName("amama");
		member.setPhone("010-241-2412");
		member.setPicture("noImage.jpg");
		
		session.update("Member.insertMember", member);
		
		MemberVO dataMember = session.selectOne("Member.selectMemberByID", member.getId());
		
		Assert.assertEquals(member.getPwd(), dataMember.getPwd());
				
	}
	
}
