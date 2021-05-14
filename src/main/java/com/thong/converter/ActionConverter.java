package com.thong.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thong.Security.CustomUserDetail;
import com.thong.dto.ActionDTO;
import com.thong.entity.Action;
import com.thong.exception.PostNotFoundException;
import com.thong.exception.UserNotFoundException;
import com.thong.repository.PostRepository;
import com.thong.repository.UserRepository;
import com.thong.service.IUserService;

@Component
public class ActionConverter implements IConverter<Action, ActionDTO> {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CustomUserDetail customUserDetail;
	
	@Autowired
	private IUserService userService;
	
	@Override
	public Action toEntity(ActionDTO o) throws Exception {
		// TODO Auto-generated method stub
		Action a = modelMapper.map(o, Action.class);
		a.setPost(postRepository.findOneById(o.getIdPost()).orElseThrow(()-> new PostNotFoundException("Post not found")));
		a.setUser(userService.findByUserName(customUserDetail.getPrincipleName()).get());
		return a;
	}

	@Override
	public ActionDTO toDTO(Action o) {
		// TODO Auto-generated method stub
		ActionDTO a = modelMapper.map(o, ActionDTO.class);
		a.setIdPost(o.getPost().getId());
		a.setIdUser(o.getUser().getId());
		return a;
	}

}
