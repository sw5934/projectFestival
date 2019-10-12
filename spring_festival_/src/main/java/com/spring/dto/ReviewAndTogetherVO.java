package com.spring.dto;

import java.sql.Date;


// 리뷰(후기)게시판 + 같이가요 게시판의 리스트들을 담아내는 VO
public class ReviewAndTogetherVO {
	private int bno; // rno, tno 공용
	private String title;
	private Date regDate;
	private String writer;
	private int unq_id;
	private int comments;
	
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getUnq_id() {
		return unq_id;
	}
	public void setUnq_id(int unq_id) {
		this.unq_id = unq_id;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	
}
