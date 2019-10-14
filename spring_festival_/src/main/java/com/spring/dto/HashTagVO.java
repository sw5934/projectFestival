package com.spring.dto;

public class HashTagVO {

	private int fno;
	private String HashTag;
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public String getHashTag() {
		return HashTag;
	}
	public void setHashTag(String hashTag) {
		HashTag = hashTag;
	}
	@Override
	public String toString() {
		return "HashTagVO [fno=" + fno + ", HashTag=" + HashTag + "]";
	}
	
	
	
	
	
}
