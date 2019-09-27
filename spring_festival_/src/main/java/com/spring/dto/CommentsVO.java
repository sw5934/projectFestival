package com.spring.dto;

import java.util.Date;

public class CommentsVO {
	private int c_no;
	private String c_writer;
	private String c_content;
	private Date c_regDate;
	private int unq_Id;
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public String getC_writer() {
		return c_writer;
	}
	public void setC_writer(String c_writer) {
		this.c_writer = c_writer;
	}
	public String getC_content() {
		return c_content;
	}
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	public Date getC_regDate() {
		return c_regDate;
	}
	public void setC_regDate(Date c_regDate) {
		this.c_regDate = c_regDate;
	}
	public int getUnq_Id() {
		return unq_Id;
	}
	public void setUnq_Id(int unq_Id) {
		this.unq_Id = unq_Id;
	}
	
	
}
