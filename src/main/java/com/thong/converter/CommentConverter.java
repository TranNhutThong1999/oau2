package com.thong.converter;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thong.Security.CustomUserDetail;
import com.thong.dto.CommentDTO;
import com.thong.dto.PostDTO;
import com.thong.dto.UserDTO;
import com.thong.entity.Comment;
import com.thong.entity.Post;
import com.thong.entity.Role;
import com.thong.entity.User;
import com.thong.exception.UserNotFoundException;
import com.thong.repository.PostRepository;
import com.thong.service.IPostService;
import com.thong.service.IUserService;

@Component
public class CommentConverter implements IConverter<Comment, CommentDTO> {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CustomUserDetail customUserDetail;
	
	@Autowired
	private IUserService userService;
	
	@Override
	public Comment toEntity(CommentDTO o) {
		// TODO Auto-generated method stub
		Comment comment = modelMapper.map(o, Comment.class);
		Post p = postRepository.findOneById(o.getIdPost()).get();
		comment.setPost(p);
		comment.setUser(userService.findByUserName(customUserDetail.getPrincipleName()).get());
		return comment;
	}

	@Override
	public CommentDTO toDTO(Comment o) {
		// TODO Auto-generated method stub
		CommentDTO m = modelMapper.map(o, CommentDTO.class);
		m.setIdPost(o.getPost().getId());
		UserDTO userDTO = modelMapper.map(o.getUser(), UserDTO.class);
		userDTO.setRoles(o.getUser().getRoles().stream().map(Role::getName).collect(Collectors.toList()));
		m.setUser(userDTO);
		return m;
	}

}
