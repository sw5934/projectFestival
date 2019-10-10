package com.spring.dto;

import java.util.Date;
import java.util.List;

public class TogetherVO {
	private int tno;
	private String t_title;
	private String t_writer;
	private String t_content;
	private Date t_regDate;
	private int t_state;
	private int t_viewcnt;
	private int f_no;
	private int unq_Id;
	private String nickname;
	
	private List<CommentsVO> commentsList;
	private int commentcount;
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public String getT_title() {
		return t_title;
	}
	public void setT_title(String t_title) {
		this.t_title = t_title;
	}
	public String getT_writer() {
		return t_writer;
	}
	public void setT_writer(String t_writer) {
		this.t_writer = t_writer;
	}
	public String getT_content() {
		return t_content;
	}
	public void setT_content(String t_content) {
		this.t_content = t_content;
	}
	public Date getT_regDate() {
		return t_regDate;
	}
	public void setT_regDate(Date t_regDate) {
		this.t_regDate = t_regDate;
	}
	public int getT_state() {
		return t_state;
	}
	public void setT_state(int t_state) {
		this.t_state = t_state;
	}
	public int getT_viewcnt() {
		return t_viewcnt;
	}
	public void setT_viewcnt(int t_viewcnt) {
		this.t_viewcnt = t_viewcnt;
	}
	public int getF_no() {
		return f_no;
	}
	public void setF_no(int f_no) {
		this.f_no = f_no;
	}
	public int getUnq_Id() {
		return unq_Id;
	}
	public void setUnq_Id(int unq_Id) {
		this.unq_Id = unq_Id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public List<CommentsVO> getCommentsList() {
		return commentsList;
	}
	public void setCommentsList(List<CommentsVO> commentsList) {
		this.commentsList = commentsList;
	}
	public int getCommentcount() {
		return commentcount;
	}
	public void setCommentcount(int commentcount) {
		this.commentcount = commentcount;
	}
	@Override
	public String toString() {
		return "TogetherVO [tno=" + tno + ", t_title=" + t_title + ", t_writer=" + t_writer + ", t_content=" + t_content
				+ ", t_regDate=" + t_regDate + ", t_state=" + t_state + ", t_viewcnt=" + t_viewcnt + ", f_no=" + f_no
				+ ", unq_Id=" + unq_Id + ", nickname=" + nickname + ", commentsList=" + commentsList + ", commentcount="
				+ commentcount + "]";
	}
	
	
}
