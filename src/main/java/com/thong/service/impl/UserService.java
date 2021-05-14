package com.thong.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.thong.Constants;
import com.thong.Security.CustomUserDetail;
import com.thong.Security.EmailService;
import com.thong.converter.UserConverter;
import com.thong.dto.UserDTO;
import com.thong.entity.Role;
import com.thong.entity.Token;
import com.thong.entity.User;
import com.thong.exception.UserExistsException;
import com.thong.exception.UserNotFoundException;
import com.thong.repository.UserRepository;
import com.thong.service.IRoleService;
import com.thong.service.IUserService;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private CustomUserDetail customUserDetail;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private Constants constants;
	
	public Page<UserDTO> findAll(int page, int limit, String sorts) {
		Pageable pageable = null;
		String[] sort = sorts.split(",");
		if (sort[1].equalsIgnoreCase("desc")) {
			pageable = PageRequest.of(page, limit, Sort.by(sort[0]).descending());
		} else {
			pageable = PageRequest.of(page, limit, Sort.by(sort[0]).ascending());
		}
		return userRepository.findAll(pageable).map(userConverter::toDTO);
	}

	@Override
	public void save(UserDTO u) throws UserExistsException {
		// TODO Auto-generated method stub
		Optional<User> check = userRepository.findOneByUserName(u.getUserName());
		if (check.isPresent()) {
			throw new UserExistsException("User existed");
		}
		u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
		User user = userConverter.toEntity(u);
		user.setActive(false);
		user.setNonBlock(true);
		Role role = roleService.findOneByName("user");
		List<Role> r = new ArrayList<Role>();
		r.add(role);
		user.setRoles(r);
		
		userRepository.save(user);
	}

	@Transactional
	@Override
	public void delete(int[] id) {
		for (int i : id) {
			userRepository.deleteById(i);;
		}
	}

	@Override
	public UserDTO findOneById(int id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findOneById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
		return userConverter.toDTO(user);
	}

	@Override
	public UserDTO findOneByUserName(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findOneByUserName(username)
				.orElseThrow(() -> new UserNotFoundException("User not found"));

		return userConverter.toDTO(user);
	}

	@Override
	public void update(UserDTO userDTO, int id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User u = userRepository.findOneById(id).orElseThrow(() -> new UserNotFoundException("User does not exist"));
		User user = userConverter.toEntity(userDTO);
		user.setId(id);
		if (user.getPassword() != null)
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public Optional<User> findByUserName(String username) {
		// TODO Auto-generated method stub
		return userRepository.findOneByUserName(username);
	}

	@Override
	public Optional<User> loadUserById(int id) {
		// TODO Auto-generated method stub
		return userRepository.findOneById(id);

	}

	@Override
	public void enable() {
		// TODO Auto-generated method stub
		User u = userRepository.findOneByUserName(customUserDetail.getPrincipleName()).get();
		emailService.sendSimpleMessage(u.getEmail(), "Spring-boot", "test");
	}
	private void sendTokenEnable(String email) {
		Token token = new Token();
		token.setTimeTokenFuture(constants.getTimeTokenExpire());
	}
	
}
