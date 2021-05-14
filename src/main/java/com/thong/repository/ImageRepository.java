package com.thong.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thong.entity.Image;
import com.thong.entity.Post;

public interface ImageRepository extends JpaRepository<Image, Long> {
	void deleteByPost(Post post);
	void deleteById(int id);
	List<Image> findImageByPost(Post post);
	Image findOneById(int id);
}
