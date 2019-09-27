package com.spring.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.controller.board.PageMaker;
import com.spring.controller.board.SearchCriteria;
import com.spring.dao.MessageDAO;
import com.spring.dto.MessageVO;

public class MessageServiceImpl implements MessageService {
	private MessageDAO messageDAO;

	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}

	@Override
	public Map<String,Object> MessageListFromSender(SearchCriteria cri, String m_sender_Id) throws SQLException {
		List<MessageVO> list = messageDAO.selectMessageListFromSender(cri, m_sender_Id);
		
		for(MessageVO message : list) {
			message.setM_receiver_Nick(messageDAO.selectReceiverNick(message.getM_receiver_Id()));
			message.setM_sender_Nick(messageDAO.selectSenderNick(message.getM_sender_Id()));
		}
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(messageDAO.selectMessageListFromSenderCount(m_sender_Id));
		
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("list", list);
		data.put("pageMaker", pageMaker);
		return data;
	}

	@Override
	public Map<String,Object> MessageListFromReceiver(SearchCriteria cri, String m_receiver_Id) throws SQLException {
		List<MessageVO> list = messageDAO.selectMessageListFromReceiver(cri, m_receiver_Id);

		for(MessageVO message : list) {
			message.setM_receiver_Nick(messageDAO.selectReceiverNick(message.getM_receiver_Id()));
			message.setM_sender_Nick(messageDAO.selectSenderNick(message.getM_sender_Id()));
		}
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(messageDAO.selectMessageListFromReceiverCount(m_receiver_Id));
		
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("list", list);
		data.put("pageMaker", pageMaker);
		return data;
	}

	@Override
	public MessageVO MessageDetail(int m_no) throws SQLException {
		MessageVO message = messageDAO.selectMessageByMno(m_no);
		message.setM_receiver_Nick(messageDAO.selectReceiverNick(message.getM_receiver_Id()));
		message.setM_sender_Nick(messageDAO.selectSenderNick(message.getM_sender_Id()));
		return message;
	}

	@Override
	public void sendMessage(MessageVO message) throws SQLException {
		messageDAO.sendMessage(message);
	}

	@Override
	public void deleteFromSender(int m_no) throws SQLException {
		messageDAO.deleteFromSender(m_no);
	}

	@Override
	public void deleteFromReceiver(int m_no) throws SQLException {
		messageDAO.deleteFromReceiver(m_no);
	}

	@Override
	public void updateCheck(int m_no) throws SQLException {
		messageDAO.updateCheck(m_no);
	}

	@Override
	public int getNextMno() throws SQLException {
		return messageDAO.getMessageSeq();
	}
}
