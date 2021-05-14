package com.thong.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.thong.Security.CustomUserDetail;
import com.thong.converter.PostConverter;
import com.thong.dto.PostDTO;
import com.thong.entity.ActionName;
import com.thong.entity.Post;
import com.thong.entity.StatePost;
import com.thong.exception.PostNotFoundException;
import com.thong.exception.UserNotFoundException;
import com.thong.repository.PostRepository;
import com.thong.service.IPostService;

@Service
public class PostService implements IPostService {
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PostConverter postConverter;

	@Autowired
	private CustomUserDetail custumUserDetail;

	@Override
	public Page<PostDTO> findAll(int page, int limit, String sorts) {
		// TODO Auto-generated method stub
		Pageable pageable = null;
		String[] sort = sorts.split(",");
		if (sort[1].equalsIgnoreCase("desc")) {
			pageable = PageRequest.of(page, limit, Sort.by("id").ascending());
		} else {
			pageable = PageRequest.of(page, limit, Sort.by("id").descending());
		}
		return postRepository.findAll(pageable).map(postConverter::toDTO);
	}

	@Override
	public PostDTO findOneById(int id) throws PostNotFoundException {
		// TODO Auto-generated method stub
		Post p = postRepository.findOneById(id).orElseThrow(() -> new PostNotFoundException("id not exist"));
		return postConverter.toDTO(p);
	}

	@Transactional
	@Override
	public void delete(int[] id) {
		// TODO Auto-generated method stub
		for (int i : id) {
			postRepository.deleteById(i);
		}
	}

	@Override
	public void update(PostDTO post, int id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		Post check = postRepository.findOneById(id).orElseThrow(() -> new UserNotFoundException("id User not exist"));
		if(!checkUser(post.getUser().getUserName())) throw new AccessDeniedException("Access denied");
		Post p = postConverter.toEntity(post);
		p.setId(id);
		postRepository.save(p);
	}

	@Override
	public void save(PostDTO postDTO) {
		// TODO Auto-generated method stub
		
		Post post = postConverter.toEntity(postDTO);
		postRepository.save(post);
	}

	@Override
	public Page<PostDTO> findAllByUser_IdAndActions_Name(int idUser, String nameAction, Pageable p) {
		// TODO Auto-generated method stub
		return postRepository.findAllByUser_IdAndActions_Name(idUser, getActionName(nameAction), p)
				.map(postConverter::toDTO);
	}

	public Page<PostDTO> findAllByState(String name, Pageable p) {
		// TODO Auto-generated method stub
		return postRepository.findAllByState(getStatePostName(name), p).map(postConverter::toDTO);
	}

	private ActionName getActionName(String name) {
		return name.equals(ActionName.CARE) ? ActionName.CARE : null;
	}

	private boolean checkUser(String userName) {
		return userName.equals(custumUserDetail.getPrincipleName());
	}

	private StatePost getStatePostName(String name) {
		StatePost state = null;
		switch (name) {
		case "DRAFT":
			state = StatePost.DRAFT;
			break;
		case "BLOCK":
			state = StatePost.BLOCK;
			break;
		case "PANDING":
			state = StatePost.PANDING;
			break;
		case "NOTAPPROVE":
			state = StatePost.NOTAPPROVE;
			break;
		case "APPROVE":
			state = StatePost.APPROVE;
			break;
		}
		return state;
	}
}
