package com.hm.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hm.springboot.model.ReturnCode;
import com.hm.springboot.model.user.User;
import com.hm.springboot.model.user.dto.ReqJoinDto;
import com.hm.springboot.model.user.dto.ReqLoginDto;
import com.hm.springboot.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	// result = 0 비정상, 1 정상, -1 DB오류, -2 아이디 중복
	@Transactional
	public int 회원가입(ReqJoinDto dto) {
		
		try {
			int result = userRepository.findByUsername(dto.getUsername());
			
			if(result == 1) {
				return ReturnCode.아이디중복;
			}else {
				return userRepository.save(dto);
			}	
		} catch (Exception e) {
			throw new RuntimeException();
		}	
	}
	
	@Transactional
	public User 로그인(ReqLoginDto dto) {
		return userRepository.findByUsernameAndPassword(dto);
	}
}
