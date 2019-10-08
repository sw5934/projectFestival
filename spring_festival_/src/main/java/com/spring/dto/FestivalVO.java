package com.spring.dto;

import java.util.Date;
import java.util.List;

public class FestivalVO {
	private int unq_Id;
	private int fno;
	private String f_writer;
	private String f_title;
	private Date f_regDate;
	private int f_viewCnt;
	private String f_content;
	private String f_name;
	private String f_location1;
	private String f_location2;
	private String f_period;
	private String f_org;
	private String f_type;
	private int vote1;
	private int vote2;
	private int commentCount;
	private List<CommentsVO> commentsList;
	public int getUnq_Id() {
		return unq_Id;
	}
	public void setUnq_Id(int unq_Id) {
		this.unq_Id = unq_Id;
	}
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public String getF_writer() {
		return f_writer;
	}
	public void setF_writer(String f_writer) {
		this.f_writer = f_writer;
	}
	public String getF_title() {
		return f_title;
	}
	public void setF_title(String f_title) {
		this.f_title = f_title;
	}
	public Date getF_regDate() {
		return f_regDate;
	}
	public void setF_regDate(Date f_regDate) {
		this.f_regDate = f_regDate;
	}
	public int getF_viewCnt() {
		return f_viewCnt;
	}
	public void setF_viewCnt(int f_viewCnt) {
		this.f_viewCnt = f_viewCnt;
	}
	public String getF_content() {
		return f_content;
	}
	public void setF_content(String f_content) {
		this.f_content = f_content;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getF_location1() {
		return f_location1;
	}
	public void setF_location1(String f_location1) {
		this.f_location1 = f_location1;
	}
	public String getF_location2() {
		return f_location2;
	}
	public void setF_location2(String f_location2) {
		this.f_location2 = f_location2;
	}
	public String getF_period() {
		return f_period;
	}
	public void setF_period(String f_period) {
		this.f_period = f_period;
	}
	public String getF_org() {
		return f_org;
	}
	public void setF_org(String f_org) {
		this.f_org = f_org;
	}
	public String getF_type() {
		return f_type;
	}
	public void setF_type(String f_type) {
		this.f_type = f_type;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	public List<CommentsVO> getCommentsList() {
		return commentsList;
	}
	public void setCommentsList(List<CommentsVO> commentsList) {
		this.commentsList = commentsList;
	}
	public int getVote1() {
		return vote1;
	}
	public void setVote1(int vote1) {
		this.vote1 = vote1;
	}
	public int getVote2() {
		return vote2;
	}
	public void setVote2(int vote2) {
		this.vote2 = vote2;
	}
	@Override
	public String toString() {
		return "FestivalVO [unq_Id=" + unq_Id + ", fno=" + fno + ", f_writer=" + f_writer + ", f_title=" + f_title
				+ ", f_regDate=" + f_regDate + ", f_viewCnt=" + f_viewCnt + ", f_content=" + f_content + ", f_name="
				+ f_name + ", f_location1=" + f_location1 + ", f_location2=" + f_location2 + ", f_period=" + f_period
				+ ", f_org=" + f_org + ", f_type=" + f_type + ", vote1=" + vote1 + ", vote2=" + vote2
				+ ", commentCount=" + commentCount + "]";
	}
	
	
	
	
	
}
