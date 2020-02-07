package com.hm.springboot.model.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ReqLoginDto {
	
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message="아이디에 한글이 입력될 수 없습니다.")
	@Size(max = 15, message = "아이디는 7~15자 이내로 등록해주십시오.")
	@NotBlank(message = "아이디를 입력하세요.")
	private String username;
	
	@Size(max = 15, message = "비밀번호는 7~15자 이내로 등록해주십시오.")
	@NotBlank(message = "비밀번호를 입력하세요.")
	private String password;
	
	public ReqLoginDto() {
		System.out.println("ReqLoginDto : ReqLoginDto() 호출됨");
	}

	public ReqLoginDto(String username, String password, String email) {
		System.out.println("ReqLoginDto : ReqLoginDto(ALL) 호출됨");
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		System.out.println("ReqJoinDto : setUsername() 호출됨");
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
