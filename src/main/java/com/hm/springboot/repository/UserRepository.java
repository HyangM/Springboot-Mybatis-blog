package com.hm.springboot.repository;

import com.hm.springboot.model.user.dto.ReqJoinDto;

public interface UserRepository {
	int save(ReqJoinDto dto);
	int findByUsername(String username);
}
