package com.spring.dto;

import java.sql.Date;

public class VoteVO {
	private String id;
	private String unq_id;
	private String f_title;
	private Date chkdate;
	private int f_seperate;
	
	


	// System.out.println(voteVO); 를 출력했을 때, 원래는 레퍼런스가 가리키는 객체의 주소가 출력이 되지만
	// 										   아래와 같이 오버라이딩을 하면 아래의 형식과 같이 출력된다.
	// 										  즉, 오버라이딩 하기 전의 toString()는 객체의 주소를 출력하는 형식.
	@Override
	public String toString() {
		System.out.println("VoteVO.toString()");
		
		return "VoteVO [id=" + id + 
				", title=" + f_title + 
				", chkdate=" + chkdate + 
					"]";
	}




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getF_title() {
		return f_title;
	}




	public void setF_content(String f_title) {
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




	public String getUnq_id() {
		return unq_id;
	}




	public void setUnq_id(String unq_id) {
		this.unq_id = unq_id;
	}

	
}
