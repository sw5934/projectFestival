package com.spring.dto;

import java.util.Date;

public class FestivalVO {
	private int unq_Id;
	private int f_no;
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
	public int getUnq_Id() {
		return unq_Id;
	}
	public void setUnq_Id(int unq_Id) {
		this.unq_Id = unq_Id;
	}
	public int getF_no() {
		return f_no;
	}
	public void setF_no(int f_no) {
		this.f_no = f_no;
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
	@Override
	public String toString() {
		return "FestivalVO [unq_Id=" + unq_Id + ", f_no=" + f_no + ", f_writer=" + f_writer + ", f_title=" + f_title
				+ ", f_regDate=" + f_regDate + ", f_viewCnt=" + f_viewCnt + ", f_content=" + f_content + ", f_name="
				+ f_name + ", f_location1=" + f_location1 + ", f_location2=" + f_location2 + ", f_period=" + f_period
				+ ", f_org=" + f_org + ", f_type=" + f_type + "]";
	}
	
	
	
}
