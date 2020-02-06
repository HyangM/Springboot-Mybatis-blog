package com.hm.springboot.model.user;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class User {
	private int id;
	private String username;
	private String pasword;
	private String email;
	private String profile;
	private Timestamp createDate;
		
	
	public User() {
		System.out.println("User : User() 호출됨");
	}

	public User(int id, String username, String pasword, String email, String profile, Timestamp createDate) {
		System.out.println("User : User(ALL) 호출됨");
		this.id = id;
		this.username = username;
		this.pasword = pasword;
		this.email = email;
		this.profile = profile;
		this.createDate = createDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		System.out.println("User : setUsername 호출됨");
		this.username = username;
	}
	public String getPasword() {
		return pasword;
	}
	public void setPasword(String pasword) {
		this.pasword = pasword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	
		
}
