package com.hm.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hm.springboot.model.user.User;
import com.hm.springboot.repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	private User user;
	
	public User getPrincipal() {
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.authentication(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("해당 유저가 없습니다.");
		}
		
		return user;
	}

}
