package com.spring.dto;

public class AuthSettingVO {
	private int period;
	private int auth_no; // 고유번호
	private String id;
	private String email;
	private String nickName;
	private String name;
	private String auth; // 권한
	
	
	
	@Override
	public String toString() {
		return "AuthSettingVO [ id="+id+ " , auth(권한)="+auth + " , 제재기간="+period+"]";
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}

	public int getAuth_no() {
		return auth_no;
	}
	public void setAuth_no(int auth_no) {
		this.auth_no = auth_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPeriod() {
		return period;
	}


	public void setPeriod(int period) {
		this.period = period;
	}
	
}
