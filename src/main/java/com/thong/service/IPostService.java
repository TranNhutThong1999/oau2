package com.thong.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thong.dto.PostDTO;
import com.thong.entity.Post;
import com.thong.entity.StatePost;
import com.thong.exception.PostNotFoundException;
import com.thong.exception.UserNotFoundException;

public interface IPostService {
	Page<PostDTO> findAll(int page, int limit, String sort);

	PostDTO findOneById(int id) throws PostNotFoundException;

	void delete(int[] id);

	void update(PostDTO post, int id) throws UserNotFoundException;

	void save(PostDTO post);

	Page<PostDTO> findAllByUser_IdAndActions_Name(int idUser, String nameAction, Pageable p);

	Page<PostDTO> findAllByState(String name, Pageable p);
}
