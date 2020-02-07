package com.hm.springboot.model.user;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Principal {
	private User user;
	private List<Role> role;
}
