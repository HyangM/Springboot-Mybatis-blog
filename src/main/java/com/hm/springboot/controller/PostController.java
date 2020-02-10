package com.hm.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;

import com.hm.springboot.model.RespCM;
import com.hm.springboot.model.post.Post;
import com.hm.springboot.model.post.dto.ReqUpdateDto;
import com.hm.springboot.model.post.dto.ReqWriteDto;
import com.hm.springboot.model.post.dto.RespListDto;
import com.hm.springboot.model.user.User;
import com.hm.springboot.service.PostService;

@Controller
public class PostController {
		
	@Autowired
	private PostService postService;
	
	@Autowired
	private HttpSession session;

	@GetMapping({"","/","post"})
	public String posts(Model model) {
		
		List<RespListDto> posts = postService.글리스트();
		model.addAttribute("posts", posts);
	
		return "/post/list";
	}
	
	@GetMapping({"/post/{id}"})
	public String post() {
		return "/post/detail";
	}
	
	// 인증 체크
	@GetMapping({"/post/write"})
	public String write() {
		return "/post/write";	
	}
	
	// 인증 체크
	@PostMapping({"/post/write"})
	public ResponseEntity<?> write(@Valid @RequestBody ReqWriteDto dto, BindingResult bindingResult) {

		User principal = (User) session.getAttribute("principal");
		dto.setUserId(principal.getId());
		
		int result =  postService.글쓰기(dto);
		if(result == 1) {
			return new ResponseEntity<RespCM>(new RespCM(200,"ok"),HttpStatus.OK);
		}else {
			return new ResponseEntity<RespCM>(new RespCM(500,"fail"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	// 인증 체크, 동일인 체크
	@GetMapping({"/post/update/{postid}"})
	public String update(@PathVariable int postid, Model model) {
		
		Post post = postService.글상세조회(postid); 
			
		model.addAttribute("post", post);
			
		return "/post/update";
				
	}	
	
	// 인증 체크, 동일인 체크
	@PutMapping({"/post/update"})
	public ResponseEntity<?> update(@RequestBody ReqUpdateDto dto) {
	
		int result = postService.글수정(dto);	
		
		if(result == 1) {						
			return new ResponseEntity<RespCM>(new RespCM(200,"ok"),HttpStatus.OK);
		}else {
			return new ResponseEntity<RespCM>(new RespCM(500,"fail"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}		
	
	// 인증 체크, 동일인 체크
	@DeleteMapping({"/post/delete/{postid}"})
	public ResponseEntity<?> delete(@PathVariable int postid) {
	
		
		int result = postService.글삭제(postid);	
		
		if(result == 1) {						
			return new ResponseEntity<RespCM>(new RespCM(200,"ok"),HttpStatus.OK);
		}else if(result == -3) {
			return new ResponseEntity<RespCM>(new RespCM(403,"fail"),HttpStatus.FORBIDDEN);
		}else {
			return new ResponseEntity<RespCM>(new RespCM(400,"fail"),HttpStatus.BAD_REQUEST);
		}
	}	
	
	// 인증 체크, 동일인 체크
	@GetMapping({"/post/detail/{postid}"})
	public String detail(@PathVariable int postid, Model model) {
		
		Post post = postService.글상세조회(postid);
		
		model.addAttribute("post", post);
		
		return "/post/detail";
	}
}
