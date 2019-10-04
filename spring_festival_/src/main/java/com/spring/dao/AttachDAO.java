package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.dto.AttachVO;

public interface AttachDAO {
	public List<AttachVO> selectAttachesByUnq_Id(int unq_Id) throws SQLException;
	
	public AttachVO selectAttachByA_no(int a_no) throws SQLException;
	
	public void insertAttach(AttachVO attach) throws SQLException;
	
	public void deleteAttach(int a_no) throws SQLException;
	
	public void deleteAllAttach(int unq_Id) throws SQLException;

}
