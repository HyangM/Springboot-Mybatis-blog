package com.hm.springboot.model.user;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// MyBatis에서 ResultType으로 담을 때 생성자 혹은 Setter 중 무엇이 호출되는지 확인 후 
@Data
@NoArgsConstructor
public class User {
	private int id;
	private String username;
	private String pasword;
	private String email;
	private String profile;
	private Timestamp createDate;
	
	@Builder
	public User(int id, String username, String pasword, String email, String profile) {
		this.username = username;
		this.pasword = pasword;
		this.email = email;
		this.profile = profile;
	}
}
