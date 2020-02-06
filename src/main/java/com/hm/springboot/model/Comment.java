package com.hm.springboot.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	private int id;
	private int userId;
	private int postId;
	private String content;
	private Timestamp createDate;
	
	@Builder
	public Comment(int userId, int postId, String content, Timestamp createDate) {
		super();
		this.userId = userId;
		this.postId = postId;
		this.content = content;
		this.createDate = createDate;
	}
}
