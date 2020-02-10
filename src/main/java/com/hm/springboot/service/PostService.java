package com.hm.springboot.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hm.springboot.model.ReturnCode;
import com.hm.springboot.model.post.Post;
import com.hm.springboot.model.post.dto.ReqUpdateDto;
import com.hm.springboot.model.post.dto.ReqWriteDto;
import com.hm.springboot.model.post.dto.RespListDto;
import com.hm.springboot.model.user.User;
import com.hm.springboot.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private HttpSession session;

	@Transactional
	public int 글쓰기(ReqWriteDto dto) {
		
		try {
			return postRepository.save(dto);
		} catch (Exception e) {
			throw new RuntimeException();
		}	
	}
	
	@Transactional
	public List<RespListDto> 글리스트(){
		
		return postRepository.findAll();
	}
	
	public Post 글상세조회(int postid){
		Post post = postRepository.findOne(postid);

		return post;

	}
	
	public int 글수정(ReqUpdateDto dto){
		
		return postRepository.update(dto);
		
	}
	
	public int 글삭제(int postid){
		// 동일인 체크 session의 principal.id == 해당 post.id로 select한 userid값
		User principal = (User)session.getAttribute("principal");
		Post post = postRepository.findOne(postid);
		
		if(principal.getId() == post.getUserId()) {
			return postRepository.delete(postid);
		}else {
			return ReturnCode.권한없음;
		}
		
	}
	

}
