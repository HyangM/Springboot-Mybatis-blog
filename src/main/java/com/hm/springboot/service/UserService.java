package com.hm.springboot.service;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	// result = 0 비정상, 1 정상, -1 DB오류, -2 아이디 중복
	@Transactional
	public int 회원가입(ReqJoinDto dto) {
		
		try {
			int result = userRepository.findByUsername(dto.getUsername());
			
			if(result == 1) {
				return ReturnCode.아이디중복;
			}else {
				// password 암호화 하기!!
				String encodePassword = passwordEncoder.encode(dto.getPassword());
				dto.setPassword(encodePassword);
				return userRepository.save(dto);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}	
	}
	
	public User 로그인(ReqLoginDto dto) {
		return userRepository.findByUsernameAndPassword(dto);
	}
	
	public int 수정완료(int id, String password, String profile, User principal) {
		
		String encodePassword = passwordEncoder.encode(password);
		int result = userRepository.update(id, password, profile);
		if(result == 1) {
			User user = userRepository.findById(id);
//			session.setAttribute("principal", user);
			principal.setPassword(encodePassword);
			principal.setProfile(user.getProfile());
			return 1;
		}else {
			return ReturnCode.오류;
		}
	}
}
