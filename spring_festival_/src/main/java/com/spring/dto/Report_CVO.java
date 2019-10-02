package com.spring.dto;

import java.util.Date;
import java.util.List;

public class Report_CVO {

	private int rpt_no_c;
	private String reporter;
	private String reporterNick;
	private Date rpt_date;
	private int unq_id;
	private String type;
	private int c_no;
	private int bno;
	private String id;
	private String nickName;
	private String contents;
	private String title;
	public int getRpt_no_c() {
		return rpt_no_c;
	}
	public void setRpt_no_c(int rpt_no_c) {
		this.rpt_no_c = rpt_no_c;
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
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getReporterNick() {
		return reporterNick;
	}
	public void setReporterNick(String reporterNick) {
		this.reporterNick = reporterNick;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@Override
	public String toString() {
		return "Report_CVO [rpt_no=" + rpt_no_c + ", reporter=" + reporter + ", rpt_date=" + rpt_date + ", unq_id="
				+ unq_id + ", type=" + type + ", c_no=" + c_no + ", id=" + id + ", c_content=" + contents + ", title="
				+ title + "]";
	}
	
	
	
}
