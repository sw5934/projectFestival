package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spring.dto.AttachVO;

public class AttachDAOImpl implements AttachDAO {
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<AttachVO> selectAttachesByUnq_Id(int unq_Id) throws SQLException {
		List<AttachVO> attachList = session.selectList("Attach-Mapper.selectAttachByUnqId", unq_Id);
		return attachList;
	}

	@Override
	public AttachVO selectAttachByA_no(int a_no) throws SQLException {
		AttachVO attach = session.selectOne("Attach-Mapper.selectAttachByAno", a_no);
		return attach;
	}

	@Override
	public void insertAttach(AttachVO attach) throws SQLException {
		session.update("Attach-Mapper.insertAttach", attach);

	}

	@Override
	public void deleteAttach(int ano) throws SQLException {
		session.update("Attach-Mapper.deleteAttach", ano);
	}

	@Override
	public void deleteAllAttach(int unq_Id) throws SQLException {
		session.update("Attach-Mapper.deleteAllAttach",unq_Id);
	}

}
