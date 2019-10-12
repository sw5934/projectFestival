package com.spring.dto;

public class SecondMemberVO {
	private String id;
	private String pwd;
	private String name;
	private String nickName;
	private String sex;
	private String tel;
	private String email;
	private int birth;
	private String gender;
	private String location;
	private String prt_pattern;
	private int info_status;
	
	
	
	
	
	
	
	public String getPrt_pattern() {
		return prt_pattern;
	}





	public void setPrt_pattern(String prt_pattern) {
		this.prt_pattern = prt_pattern;
	}



	
	public String getSex() {
		return sex;
	}





	public void setSex(String sex) {
		this.sex = sex;
	}




	
	
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





	public String getGender() {
		return gender;
	}





	public void setGender(String gender) {
		this.gender = gender;
	}





	public String getLocation() {
		return location;
	}





	public void setLocation(String location) {
		this.location = location;
	}





	public int getInfo_status() {
		return info_status;
	}





	public void setInfo_status(int info_status) {
		this.info_status = info_status;
	}





	@Override
	public String toString() {
		
		
		return "memberVO [ id="+id+"  pwd="+pwd+"  name="+name+"  nickName="+nickName+"  tel="+tel+
				"  birth="+birth+"  location="+location+
				"  info_status="+info_status+"  email="+email+
//				"  prt_pattern="+prt_pattern + 
				"  gender="+gender+  " ]";
	}
	
}
