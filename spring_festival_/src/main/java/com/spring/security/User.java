package com.spring.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.dto.MemberVO;

public class User implements UserDetails {

	private MemberVO member;
	
	public User() {};
	public User(MemberVO member) {
		super();
		this.member = member;
	}
	
	public MemberVO getMemberVO() {
		return this.member;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return member.getPwd();
	}

	@Override
	public String getUsername() {
		return member.getId();
	}

	@Override
	public boolean isAccountNonExpired() {	// 기간제 계정의 경우 기간만료 여부
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {	// 사용 제제 여부
		/*boolean result = false;
		int enabled = member.getEnabled();	// 0:휴면, 1:정상, 2:정지
		if(enabled == 1) {result = true;}
		return result;*/
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {	// 인증정보 만료 여부
		/*return true;*/
		return false;
	}

	@Override
	public boolean isEnabled() {	// 휴면계정 여부
		/*boolean result = false;
		int enabled = member.getEnabled();	// 0:휴면, 1:정상, 2:정지
		if(enabled == 1) {result = true;}
		return result;*/
		return false;
	}

		
}
