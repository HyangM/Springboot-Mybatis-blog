package com.hm.springboot.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	private int id;
	private String title;
	private String content;
	private int userId;
	private Timestamp createDate;
	
	@Builder
	public Post(String title, String content, int userId, Timestamp createDate) {
		super();
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.createDate = createDate;
	}


}
