package com.hm.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hm.springboot.model.post.Post;
import com.hm.springboot.model.post.dto.ReqUpdateDto;
import com.hm.springboot.model.post.dto.ReqWriteDto;
import com.hm.springboot.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;

	@Transactional
	public int 글쓰기(ReqWriteDto dto) {
		
		try {
			return postRepository.save(dto);
		} catch (Exception e) {
			throw new RuntimeException();
		}	
	}
	
	@Transactional
	public List<Post> 글리스트(){
		
		return postRepository.findAll();
	}
	
	@Transactional
	public Post 글상세조회(int postid){
		
		return postRepository.findOne(postid);
	}
	
	@Transactional
	public int 글수정(ReqUpdateDto dto){
		
		return postRepository.update(dto);
	}
	
	@Transactional
	public int 글삭제(int postid){
		
		return postRepository.delete(postid);
	}
	

}
