package com.thong.controller;

import javax.validation.Valid;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.thong.api.input.UpdateInput;
import com.thong.dto.UserDTO;
import com.thong.entity.User;
import com.thong.exception.UserNotFoundException;
import com.thong.exception.ValidationBindingResult;
import com.thong.service.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private ValidationBindingResult validationBindingResult;

	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int limit, @RequestParam(defaultValue = "id,asc") String sorts) {
		Page<UserDTO> list = userService.findAll(page, limit, sorts);
		return new ResponseEntity<Page<UserDTO>>(list, HttpStatus.ACCEPTED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOneByID(@PathVariable("id") int id) {
		try {
			UserDTO list = userService.findOneById(id);
			return new ResponseEntity<UserDTO>(list, HttpStatus.ACCEPTED);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());// handleException.handle(e.getMessage())
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody UserDTO user, BindingResult bindingResult,
			@PathVariable(name = "id") int id) throws JsonProcessingException {
		try {
			ResponseEntity<?> error = validationBindingResult.process(bindingResult);
			if (error != null) {
				return error;
			}
			userService.update(user, id);
			return ResponseEntity.ok("success");
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody int[] id) {
		userService.delete(id);
		return ResponseEntity.ok("success");
	}

	@GetMapping("/enable")
	public ResponseEntity<?> enableUser() {
		userService.enable();
		return ResponseEntity.ok("success");
	}
//	@PostMapping
//	public ResponseEntity<?> enable(@RequestParam String token){
//		
//	}
}
