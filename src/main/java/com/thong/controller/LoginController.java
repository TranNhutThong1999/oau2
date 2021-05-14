package com.thong.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.thong.Cache;
import com.thong.JWT.JwtTokenProvider;
import com.thong.api.input.LoginInput;
import com.thong.api.output.LoginOutput;
import com.thong.dto.PostDTO;
import com.thong.dto.UserDTO;
import com.thong.exception.UserExistsException;
import com.thong.exception.ValidationBindingResult;
import com.thong.service.IUserService;

@CrossOrigin
@RestController
public class LoginController {
	@Autowired
	private ValidationBindingResult validationBindingResult;

	@Autowired
	private JwtTokenProvider jwt;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private IUserService userService;
	
	@Autowired
	private Cache cache;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginInput login, BindingResult bindingResult) {
		System.out.println(login.getPassword());
		ResponseEntity<?> error = validationBindingResult.process(bindingResult);
		if (error != null)
			return error;
		try {
			Authentication auth = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(auth);
			String jsonWebToken = jwt.generateToken(auth);
			UserDTO u = userService.findOneByUserName(auth.getName());
			LoginOutput l = new LoginOutput(true, jsonWebToken, u);
			return ResponseEntity.ok(l);
		} catch (Exception e) {
			// return new ResponseEntity<Object>(handleException.handle("wrong username or
			// password "), HttpStatus.BAD_REQUEST);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody UserDTO user, BindingResult bindingResult) {
		ResponseEntity<?> error = validationBindingResult.process(bindingResult);
		if (error != null) {
			System.out.println("err");
			return error;
		}
		try {

			userService.save(user);

			return ResponseEntity.ok("ok");
		} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	@PostMapping("/logouttt")
	public ResponseEntity<?> logout(@RequestHeader (name="Authorization") String token) {
		System.out.println(token);
		cache.addTokenExprireToMap(token.substring(7));
		return ResponseEntity.ok("ok");
	}
}
