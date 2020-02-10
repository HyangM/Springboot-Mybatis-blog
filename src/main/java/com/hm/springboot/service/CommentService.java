package com.hm.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.springboot.model.ReturnCode;
import com.hm.springboot.model.comment.dto.ReqDetailDto;
import com.hm.springboot.model.comment.dto.RespDetailDto;
import com.hm.springboot.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	public RespDetailDto 댓글쓰기(ReqDetailDto dto) {
		
		int result = commentRepository.save(dto);
		
		if(result == 1) {
			int id = dto.getId();
			RespDetailDto respDetailDto = commentRepository.findById(id);
			if(respDetailDto != null) {
				return respDetailDto;
			}else {
				return null;
			}
			
		}else {	//댓글쓰기 실패
			return null;
		}
	}
	
	public int 댓글삭제(int commentId) {
		
		return commentRepository.delete(commentId);
	}
}
