package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.MessageVO;

public interface MessageDAO {
	public List<MessageVO> selectMessageListFromSender(SearchCriteria cri, String m_sender_Id) throws SQLException;
	
	public List<MessageVO> selectMessageListFromReceiver(SearchCriteria cri, String m_receiver_Id) throws SQLException;

	public int selectMessageListFromSenderCount(String m_sender_Id) throws SQLException;
	
	public int selectMessageListFromReceiverCount(String m_receiver_Id) throws SQLException;
	
	public MessageVO selectMessageByMno(int m_no) throws SQLException;
	
	public int getMessageSeq() throws SQLException;

	public void sendMessage(MessageVO message) throws SQLException;
	
	public void deleteFromSender(int m_no) throws SQLException;
	
	public void deleteFromReceiver(int m_no) throws SQLException;

	public void updateCheck(int m_no) throws SQLException;

	public String selectReceiverNick(String m_receiver_Id) throws SQLException;

	public String selectSenderNick(String m_sender_Id) throws SQLException;

}
