package com.hm.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.relation.Role;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hm.springboot.model.RespCM;
import com.hm.springboot.model.ReturnCode;
import com.hm.springboot.model.user.User;
import com.hm.springboot.model.user.dto.ReqJoinDto;
import com.hm.springboot.model.user.dto.ReqLoginDto;
import com.hm.springboot.service.UserService;

@Controller
public class UserController {

	private static final String TAG="UserController : ";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/user/join")
	public String join() {
		return "/user/join";
	}
	
	@GetMapping("/user/login")
	public String login() {
		return "/user/login";
	}
	
	@GetMapping("/user/logout")
	public String logout() {		
		session.invalidate();		
		return "redirect:/";
	}
	
	// 인증, 동일인 체크
	@GetMapping({"/user/profile/{id}"})
	public String profile(@PathVariable int id) {
		
		User principal = (User)session.getAttribute("principal");
		
		if(principal.getId() == id) {
			return "/user/profile";
		}else {
			// 잘못된 접근입니다.권한이 없습니다.
			return "/user/login";
		}
	}
	
	// 메시지 컨버터(Jackson Mapper)는 request받을 때 setter로 호출한다.
	@PostMapping("/user/join")
	public ResponseEntity<?> join(@Valid @RequestBody ReqJoinDto dto, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError error:bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		
		int result = userService.회원가입(dto);
		
		if(result == -2) {
			return new ResponseEntity<RespCM>(new RespCM(ReturnCode.아이디중복,"아이디중복"),HttpStatus.OK);
//			return new ResponseEntity<RespCM>(new RespCM(-2,"아이디중복"),HttpStatus.OK);
		}else if(result == 1){
			return new ResponseEntity<RespCM>(new RespCM(200,"ok"),HttpStatus.OK);
		}else {
			return new ResponseEntity<RespCM>(new RespCM(500,"fail"),HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@PostMapping("/user/login")
	public ResponseEntity<?> login(@Valid @RequestBody ReqLoginDto dto, BindingResult bindingResult) {
		
		// request 검증 = AOP로 처리할 예정
		//서비스 호출
		User user = userService.로그인(dto);
//		Role role1 = new Role(1, 1, "2");
//		List<Role> roles = new ArrayList<>();
//		roles.add(role1);
		
		User principal = userService.로그인(dto);
		
		if(principal != null) {
			session.setAttribute("principal", principal);
			return new ResponseEntity<RespCM>(new RespCM(200,"ok"),HttpStatus.OK);
		}else {
			return new ResponseEntity<RespCM>(new RespCM(400,"fail"),HttpStatus.BAD_REQUEST);
		}
	}	
}
