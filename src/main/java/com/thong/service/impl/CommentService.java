package com.thong.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.thong.converter.CommentConverter;
import com.thong.dto.CommentDTO;
import com.thong.entity.Comment;
import com.thong.repository.CommentRepository;
import com.thong.service.ICommentService;

@Service
public class CommentService implements ICommentService {

	@Autowired
	private CommentConverter commentConverter;

	@Autowired
	private CommentRepository commentRepository;

	public Page<CommentDTO> findAll(int page, int limit, String sorts) {
		Pageable pageable = null;
		String[] sort = sorts.split(",");
		if (sort[1].equalsIgnoreCase("desc")) {
			pageable = PageRequest.of(page, limit, Sort.by(sort[0]).descending());
		} else {
			pageable = PageRequest.of(page, limit, Sort.by(sort[0]).ascending());
		}
		return commentRepository.findAll(pageable).map(commentConverter::toDTO);
	}

	@Transactional
	@Override
	public void delete(int[] id) {
		// TODO Auto-generated method stub
		for (int i : id) {
			commentRepository.deleteById(i);
		}

	}

	@Override
	public void save(CommentDTO cm) {
		// TODO Auto-generated method stub
		commentRepository.save(commentConverter.toEntity(cm));
	}

	@Override
	public void update(CommentDTO cm, int id) {
		// TODO Auto-generated method stub
		Comment m = commentConverter.toEntity(cm);
		m.setId(id);
		commentRepository.save(m);
	}

}
