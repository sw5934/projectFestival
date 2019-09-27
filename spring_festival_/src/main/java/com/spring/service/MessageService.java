package com.spring.service;

import java.sql.SQLException;
import java.util.Map;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.MessageVO;

public interface MessageService {
	
	public Map<String,Object> MessageListFromSender(SearchCriteria cri, String m_sender_Id) throws SQLException;
	
	public Map<String,Object> MessageListFromReceiver(SearchCriteria cri, String m_receiver_Id) throws SQLException;
	
	public MessageVO MessageDetail(int m_no) throws SQLException;

	public void sendMessage(MessageVO message) throws SQLException;
	
	public void deleteFromSender(int m_no) throws SQLException;
	
	public void deleteFromReceiver(int m_no) throws SQLException;

	public void updateCheck(int m_no) throws SQLException;
	
	public int getNextMno() throws SQLException;

}
