package com.spring.dto;

import java.sql.Date;

public class CommentsBoardVO {
	private String category;
	private int unq_id;
	private int bno; // fno, tno, rno 공용
	private String title;
	private String comment;
	private String writer;
	private Date regDate;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getUnq_id() {
		return unq_id;
	}
	public void setUnq_id(int unq_id) {
		this.unq_id = unq_id;
	}
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	
	// category, unq_id, bno, title, comment, writer, regDate
	@Override
	public String toString() {
		return "CommentBoardVO [ category = " + category + ", unq_id = " + unq_id
				+ ", bno = " + bno + ", title = " + title + ", comment = " + comment
				+ ", comment = " + comment + ", writer = " + writer
				+ ", regDate = " + regDate + "]";
	}
}
