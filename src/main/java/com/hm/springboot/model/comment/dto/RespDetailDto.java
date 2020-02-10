package com.hm.springboot.model.comment.dto;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

import com.hm.springboot.model.RespCM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespDetailDto {
	
	@Autowired
	private RespCM status;
	
	private int id;
	private int userId;
	private int postId;
	private String content;
	private Timestamp createDate;
	private String username;
	
}
