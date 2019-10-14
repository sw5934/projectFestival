package com.spring.dto;

public class HoldingVO {
	private String title;
	private String hashTag;
	private String [] arrHash;
	private int comments; // 각각의 축제글의 댓글 총 갯수
	private int unq_id;
	private String id;
	private int fno;
	

	public String[] getArrHash() {
		return arrHash;
	}
	public void setArrHash(String[] arrHash) {
		this.arrHash = arrHash;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHashTag() {
		return hashTag;
	}
	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}
	
	@Override
	public String toString() {
		return "HoldingVO [ title = " + title + ", hashTag = " + hashTag + ", comments = " + comments +" ]";		
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
}
