package com.thong.converter;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thong.dto.UserDTO;
import com.thong.entity.Role;
import com.thong.entity.User;

@Component
public class UserConverter implements IConverter<User, UserDTO> {
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public User toEntity(UserDTO o) {
//		// TODO Auto-generated method stub
		User user = modelMapper.map(o, User.class);
		return user;
	}

	@Override
	public UserDTO toDTO(User o) {
		// TODO Auto-generated method stub
		UserDTO userDTO = modelMapper.map(o, UserDTO.class);
		userDTO.setRoles(o.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
		return userDTO;
	}
}
