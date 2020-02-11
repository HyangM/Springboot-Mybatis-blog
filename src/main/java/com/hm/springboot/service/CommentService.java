package com.hm.springboot.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.springboot.model.ReturnCode;
import com.hm.springboot.model.comment.dto.ReqDetailDto;
import com.hm.springboot.model.comment.dto.RespDetailDto;
import com.hm.springboot.model.user.User;
import com.hm.springboot.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;	
	
	@Autowired
	private HttpSession session;
	
	
	public List<RespDetailDto> 댓글목록보기(int postid) {

		return commentRepository.findByAll(postid);
		
		
	}
	
	
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
		
		// 댓글 작성자 
		RespDetailDto comment = commentRepository.findById(commentId);
		
		// 현재 로그인한 주체
		User principal = (User)session.getAttribute("principal");
		
		if(comment.getUserId() == principal.getId()) {
			return commentRepository.delete(commentId);	
		}else {
			return ReturnCode.권한없음;
		}
	}
	
}
