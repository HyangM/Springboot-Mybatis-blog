package com.hm.springboot.repository;

import com.hm.springboot.model.comment.dto.ReqDetailDto;
import com.hm.springboot.model.comment.dto.RespDetailDto;

public interface CommentRepository {
	public int save(ReqDetailDto dto);
	public RespDetailDto findById(int id);
	public int delete(int commentId);
}
