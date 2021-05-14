package com.thong.service;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thong.dto.UserDTO;
import com.thong.entity.User;
import com.thong.exception.UserExistsException;
import com.thong.exception.UserNotFoundException;

public interface IUserService {
	Page<UserDTO> findAll(int page,int limit,String sorts);
	void save(UserDTO u) throws UserExistsException;
	void delete(int[] id);
	void update(UserDTO user,int id) throws UserNotFoundException;
	UserDTO findOneById(int id) throws UserNotFoundException ;
	Optional<User> loadUserById(int id) ;
	UserDTO findOneByUserName(String username) throws UserNotFoundException;
	Optional<User> findByUserName(String username);
	void enable();
}
