package com.hm.springboot.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private int id;
	private String username;
	private String pasword;
	private String email;
	private String profile;
	private Timestamp createDate;
	
	@Builder
	public User(String username, String pasword, String email, String profile, Timestamp createDate) {
		super();
		this.username = username;
		this.pasword = pasword;
		this.email = email;
		this.profile = profile;
		this.createDate = createDate;
	}
		
}
