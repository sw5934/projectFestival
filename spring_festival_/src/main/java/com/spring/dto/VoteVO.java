package com.spring.dto;

import java.sql.Date;

public class VoteVO {
	private String id;
	private String unq_id;
	private String f_title;
	private Date chkdate;
	private int f_seperate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUnq_id() {
		return unq_id;
	}
	public void setUnq_id(String unq_id) {
		this.unq_id = unq_id;
	}
	public String getF_title() {
		return f_title;
	}
	public void setF_title(String f_title) {
		this.f_title = f_title;
	}
	public Date getChkdate() {
		return chkdate;
	}
	public void setChkdate(Date chkdate) {
		this.chkdate = chkdate;
	}
	public int getF_seperate() {
		return f_seperate;
	}
	public void setF_seperate(int f_seperate) {
		this.f_seperate = f_seperate;
	}
	
	

	
}
