package com.thong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thong.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
		void deleteById(int id);
}
