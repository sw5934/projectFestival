package com.spring.dto;

import java.util.Date;

public class MessageVO {
	private int m_no;
	private String m_content;
	private String m_sender_Nick;
	private String m_receiver_Nick;
	private String m_sender_Id;
	private String m_receiver_Id;
	private Date m_sendDate;
	private int m_status;
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getM_content() {
		return m_content;
	}
	public void setM_content(String m_content) {
		this.m_content = m_content;
	}
	public String getM_sender_Nick() {
		return m_sender_Nick;
	}
	public void setM_sender_Nick(String m_sender_Nick) {
		this.m_sender_Nick = m_sender_Nick;
	}
	public String getM_receiver_Nick() {
		return m_receiver_Nick;
	}
	public void setM_receiver_Nick(String m_receiver_Nick) {
		this.m_receiver_Nick = m_receiver_Nick;
	}
	public String getM_sender_Id() {
		return m_sender_Id;
	}
	public void setM_sender_Id(String m_sender_Id) {
		this.m_sender_Id = m_sender_Id;
	}
	public String getM_receiver_Id() {
		return m_receiver_Id;
	}
	public void setM_receiver_Id(String m_receiver_Id) {
		this.m_receiver_Id = m_receiver_Id;
	}
	public Date getM_sendDate() {
		return m_sendDate;
	}
	public void setM_sendDate(Date m_sendDate) {
		this.m_sendDate = m_sendDate;
	}
	public int getM_status() {
		return m_status;
	}
	public void setM_status(int m_status) {
		this.m_status = m_status;
	}
	@Override
	public String toString() {
		return "MessageVO [m_no=" + m_no + ", m_content=" + m_content + ", m_sender_Nick=" + m_sender_Nick
				+ ", m_receiver_Nick=" + m_receiver_Nick + ", m_sender_Id=" + m_sender_Id + ", m_receiver_Id="
				+ m_receiver_Id + ", m_sendDate=" + m_sendDate + "]";
	}
	
}
