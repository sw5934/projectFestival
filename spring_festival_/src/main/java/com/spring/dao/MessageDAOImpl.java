package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.MessageVO;

public class MessageDAOImpl implements MessageDAO{

	private SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<MessageVO> selectMessageListFromSender(SearchCriteria cri, String m_sender_Id) throws SQLException {
		int offset=cri.getPageStartRowNum();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		
		List<MessageVO> message =
				session.selectList("Message-Mapper.selectMessageListFromSender",m_sender_Id,rowBounds);
		return message;
	}

	@Override
	public List<MessageVO> selectMessageListFromReceiver(SearchCriteria cri, String m_receiver_Id) throws SQLException {
		int offset=cri.getPageStartRowNum();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		
		List<MessageVO> message =
				session.selectList("Message-Mapper.selectMessageListFromReceiver",m_receiver_Id,rowBounds);
		return message;
	}
	
	public int selectMessageListFromSenderCount(String m_sender_Id) throws SQLException {
		return session.selectList("Message-Mapper.selectMessageListFromSender",m_sender_Id).size();
	}

	@Override
	public int selectMessageListFromReceiverCount(String m_receiver_Id) throws SQLException {
		return session.selectList("Message-Mapper.selectMessageListFromReceiver",m_receiver_Id).size();
	}

	@Override
	public MessageVO selectMessageByMno(int m_no) throws SQLException {
		MessageVO message =
				session.selectOne("Message-Mapper.selectMessageByMno",m_no);
		return message;
	}

	@Override
	public int getMessageSeq() throws SQLException {
		return session.selectOne("Message-Mapper.getMessageSeq");
	}

	@Override
	public void sendMessage(MessageVO message) throws SQLException {
		session.update("Message-Mapper.sendMessage",message);
	}

	@Override
	public void deleteFromSender(int m_no) throws SQLException {
		session.update("Message-Mapper.deleteFromSender",m_no);
	}

	@Override
	public void deleteFromReceiver(int m_no) throws SQLException {
		session.update("Message-Mapper.deleteFromReceiver",m_no);
	}

	@Override
	public void updateCheck(int m_no) throws SQLException {
		session.update("Message-Mapper.updateCheck",m_no);
	}
	

	public String selectReceiverNick(String m_receiver_Id) throws SQLException{
		return session.selectOne("Message-Mapper.selectReceiverNick", m_receiver_Id);
	}

	public String selectSenderNick(String m_sender_Id) throws SQLException{
		return session.selectOne("Message-Mapper.selectSenderNick", m_sender_Id);
	}

}
