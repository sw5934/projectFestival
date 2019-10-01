package com.spring.dto;

import java.util.Date;
import java.util.List;

public class ReportVO {

	private int rpt_no;
	private String reporter;
	private Date rpt_date;
	private int unq_id;
	private String type;
	private int bno;
	private String id;
	private String title;
	public int getRpt_no() {
		return rpt_no;
	}
	public void setRpt_no(int rpt_no) {
		this.rpt_no = rpt_no;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	public Date getRpt_date() {
		return rpt_date;
	}
	public void setRpt_date(Date rpt_date) {
		this.rpt_date = rpt_date;
	}
	public int getUnq_id() {
		return unq_id;
	}
	public void setUnq_id(int unq_id) {
		this.unq_id = unq_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "ReportVO [rpt_no=" + rpt_no + ", reporter=" + reporter + ", rpt_date=" + rpt_date + ", unq_id=" + unq_id
				+ ", type=" + type + ", bno=" + bno + ", id=" + id + ", title=" + title + "]";
	}
	
	
	
	
	
}
