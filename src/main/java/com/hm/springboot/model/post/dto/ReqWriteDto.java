package com.hm.springboot.model.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqWriteDto {
	private String title;
	private String content;
	private int userId;
}
