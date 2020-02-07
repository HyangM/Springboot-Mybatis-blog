package com.hm.springboot.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Role {
	private int id;
	private int userId;
	private String roleName;
}
