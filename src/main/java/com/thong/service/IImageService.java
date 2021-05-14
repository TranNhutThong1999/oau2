package com.thong.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.thong.dto.ImageDTO;
import com.thong.exception.PostNotFoundException;

public interface IImageService {
	void save(int idPost, MultipartFile file) throws IOException;
	List<ImageDTO> findByPostId(int idPost) throws PostNotFoundException;
	void delete(int[] id) throws IOException;
}
