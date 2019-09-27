package com.spring.dto;

import java.util.List;

public class MemberVO {

	private String id;
	private String pwd;
	private String name;
	private String nickName;
	private String tel;
	private String email;
	private int birth;
	private String sex;
	private String location;
	private String prtPattern;
	private int infoStatus;
	
	private List<Integer> authority;
	private int failCnt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getBirth() {
		return birth;
	}
	public void setBirth(int birth) {
		this.birth = birth;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPrtPattern() {
		return prtPattern;
	}
	public void setPrtPattern(String prtPattern) {
		this.prtPattern = prtPattern;
	}
	public int getInfoStatus() {
		return infoStatus;
	}
	public void setInfoStatus(int infoStatus) {
		this.infoStatus = infoStatus;
	}
	
	public List<Integer> getAuthority() {
		return authority;
	}
	public void setAuthority(List<Integer> authority) {
		this.authority = authority;
	}
	
	public int getFailCnt() {
		return failCnt;
	}
	public void setFail_cnt(int failCnt) {
		this.failCnt = failCnt;
	}
	
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pwd=" + pwd + ", name=" + name + ", nickName=" + nickName + ", tel=" + tel
				+ ", email=" + email + ", birth=" + birth + ", sex=" + sex + ", location=" + location + ", prtPattern="
				+ prtPattern + ", infoStatus=" + infoStatus + ", authority=" + authority + ", failCnt=" + failCnt
				+ "]";
	}
	
}
