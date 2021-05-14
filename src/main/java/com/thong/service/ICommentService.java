package com.thong.service;

import org.springframework.data.domain.Page;

import com.thong.dto.CommentDTO;


public interface ICommentService {
	Page<CommentDTO> findAll(int page, int limit, String sorts);
	void delete(int[] id);
	void save(CommentDTO cm);
	void update(CommentDTO cm,int id);
	
}
