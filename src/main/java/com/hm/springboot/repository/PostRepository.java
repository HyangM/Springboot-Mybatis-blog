package com.hm.springboot.repository;

import java.util.List;

import com.hm.springboot.model.post.Post;
import com.hm.springboot.model.post.dto.ReqUpdateDto;
import com.hm.springboot.model.post.dto.ReqWriteDto;

public interface PostRepository {
	int save(ReqWriteDto dto);
	
	List<Post> findAll();
	
	Post findOne(int postid);
	
	int update(ReqUpdateDto dto);
	
	int delete(int postid);
}
