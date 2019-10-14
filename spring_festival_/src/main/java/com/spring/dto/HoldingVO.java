package com.spring.dto;

import java.util.List;

public class HoldingVO {
	private String title;
	private List<HashTagVO> hashTag;
	private int comments; // 각각의 축제글의 댓글 총 갯수
	private int unq_id;
	private int fno;
	private String id;
	
	
	@Override
	public String toString() {
		if(hashTag==null) {
			return "HoldingVO [ title = " + title + ", hashTag = 0개, comments = " + comments +"개 ]";
		}
		if(hashTag!=null) {
			return "HoldingVO [ title = " + title + ", hashTag = " + hashTag + ", comments = " + comments +"개 ]";
		}
		return "";
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	public int getUnq_id() {
		return unq_id;
	}
	public void setUnq_id(int unq_id) {
		this.unq_id = unq_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}


	public List<HashTagVO> getHashTag() {
		return hashTag;
	}


	public void setHashTag(List<HashTagVO> hashTag) {
		this.hashTag = hashTag;
	}
}
