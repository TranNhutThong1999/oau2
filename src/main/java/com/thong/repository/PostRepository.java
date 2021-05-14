package com.thong.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thong.entity.Action;
import com.thong.entity.ActionName;
import com.thong.entity.Post;
import com.thong.entity.StatePost;

public interface PostRepository extends JpaRepository<Post, Long> {
	Optional<Post> findOneById(int id);
	void deleteById(int id);
	Page<Post> findAllByUser_IdAndActions_Name(int idUser,ActionName nameAction, Pageable p);
	Page<Post> findAllByState(StatePost statePost, Pageable p);
}
