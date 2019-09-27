package com.spring.dto;

import java.util.Date;
import java.util.List;

public class ReviewVO {
	private int rno;
	private String r_title;
	private String id;
	private String r_content;
	private int r_viewcnt;				
	private int fno;
	private int r_like;				//좋아요
	private int r_score;				//평점
	private Date r_regDate;
	private int unq_Id;
	private List<CommentsVO> commentsList;
	private List<AttachVO> attachList;
	private int commentcount;

	public String getR_title() {
		return r_title;
	}
	public void setR_title(String r_title) {
		this.r_title = r_title;
	}
	
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public int getR_viewcnt() {
		return r_viewcnt;
	}
	public void setR_viewcnt(int r_viewcnt) {
		this.r_viewcnt = r_viewcnt;
	}
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public int getR_like() {
		return r_like;
	}
	public void setR_like(int r_like) {
		this.r_like = r_like;
	}
	public int getR_score() {
		return r_score;
	}
	public void setR_score(int r_score) {
		this.r_score = r_score;
	}
	public Date getR_regDate() {
		return r_regDate;
	}
	public void setR_regDate(Date r_regDate) {
		this.r_regDate = r_regDate;
	}
	public int getCommentcount() {
		return commentcount;
	}
	public void setCommentcount(int commentcount) {
		this.commentcount = commentcount;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<AttachVO> getAttachList() {
		return attachList;
	}
	public void setAttachList(List<AttachVO> attachList) {
		this.attachList = attachList;
	}
	public List<CommentsVO> getCommentsList() {
		return commentsList;
	}
	public void setCommentsList(List<CommentsVO> commentsList) {
		this.commentsList = commentsList;
	}
	public int getUnq_Id() {
		return unq_Id;
	}
	public void setUnq_Id(int unq_Id) {
		this.unq_Id = unq_Id;
	}
	
}
