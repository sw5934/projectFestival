package com.spring.dto;

import java.util.Date;

public class AttachVO {
	private int a_no;
	private String a_uploadPath;
	private String a_filename;
	private String a_fileType;
	private Date a_regDate;
	private String a_uuid;
	private int unq_Id;
	
	public int getA_no() {
		return a_no;
	}
	public void setA_no(int a_no) {
		this.a_no = a_no;
	}
	public String getA_uploadPath() {
		return a_uploadPath;
	}
	public void setA_uploadPath(String a_uploadPath) {
		this.a_uploadPath = a_uploadPath;
	}
	public String getA_filename() {
		return a_filename;
	}
	public void setA_filename(String a_filename) {
		this.a_filename = a_filename;
	}
	public String getA_fileType() {
		return a_fileType;
	}
	public void setA_fileType(String a_fileType) {
		this.a_fileType = a_fileType;
	}
	public Date getA_regDate() {
		return a_regDate;
	}
	public void setA_regDate(Date a_regDate) {
		this.a_regDate = a_regDate;
	}
	public String getA_uuid() {
		return a_uuid;
	}
	public void setA_uuid(String a_uuid) {
		this.a_uuid = a_uuid;
	}
	public int getUnq_Id() {
		return unq_Id;
	}
	public void setUnq_Id(int unq_Id) {
		this.unq_Id = unq_Id;
	}
	@Override
	public String toString() {
		return "AttachVO [a_no=" + a_no + ", a_uploadPath=" + a_uploadPath + ", a_filename=" + a_filename
				+ ", a_fileType=" + a_fileType + ", a_regDate=" + a_regDate + ", a_uuid=" + a_uuid + ", unq_Id="
				+ unq_Id + "]";
	}
	

}
