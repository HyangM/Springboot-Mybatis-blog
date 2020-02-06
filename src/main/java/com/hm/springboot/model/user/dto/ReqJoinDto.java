package com.hm.springboot.model.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ReqJoinDto {
	
	@Size(min = 7, max = 15, message = "아이디는 7~15자 이내로 등록해주십시오.")
	@NotBlank(message = "아이디를 입력하세요.")
	private String username;
	
	@Size(min = 7, max = 15, message = "비밀번호는 7~15자 이내로 등록해주십시오.")
	@NotBlank(message = "비밀번호를 입력하세요.")
	private String password;
	
	@Size(min = 5, max = 30, message = "이메일 기준길이를 초과했습니다.")
	@Email(message = "이메일 양식이 틀렸습니다.")
	@NotBlank(message = "이메일을 입력하세요.")
	private String email;
	
	public ReqJoinDto() {
		System.out.println("ReqJoinDto : RequJoinDto() 호출됨");
	}

	public ReqJoinDto(String username, String password, String email) {
		System.out.println("ReqJoinDto : RequJoinDto(ALL) 호출됨");
		this.username = username;
		this.password = password;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
		
}
