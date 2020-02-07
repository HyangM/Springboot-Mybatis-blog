package com.hm.springboot.repository;

import com.hm.springboot.model.user.User;
import com.hm.springboot.model.user.dto.ReqJoinDto;
import com.hm.springboot.model.user.dto.ReqLoginDto;

public interface UserRepository {
	int save(ReqJoinDto dto);
	int findByUsername(String username);
	User findByUsernameAndPassword(ReqLoginDto dto);
}
