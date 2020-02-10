package com.hm.springboot.model.post.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespListDto {
	private int id;
	private String title;
	private String content;
	private String username;
	private int userId;
	private Timestamp createDate;
}
