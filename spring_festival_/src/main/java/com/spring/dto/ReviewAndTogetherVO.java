package com.spring.dto;

import java.sql.Date;

public class ReviewAndTogetherVO {
	private int rno;
	private String r_title;
	private Date r_regDate;
	private String r_writer;
	
	
	
	
	
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public String getR_title() {
		return r_title;
	}
	public void setR_title(String r_title) {
		this.r_title = r_title;
	}
	public Date getR_regDate() {
		return r_regDate;
	}
	public void setR_regDate(Date r_regDate) {
		this.r_regDate = r_regDate;
	}
	public String getR_writer() {
		return r_writer;
	}
	public void setR_writer(String r_writer) {
		this.r_writer = r_writer;
	}
}
