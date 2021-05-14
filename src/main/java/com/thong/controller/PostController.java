package com.thong.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.thong.dto.ActionDTO;
import com.thong.dto.PostDTO;
import com.thong.entity.Action;
import com.thong.exception.PostNotFoundException;
import com.thong.exception.UserNotFoundException;
import com.thong.exception.ValidationBindingResult;
import com.thong.service.IPostService;

@RestController
@RequestMapping("/posts")
public class PostController {
	@Autowired
	private IPostService postService;

	@Autowired
	private ValidationBindingResult validationBindingResult;

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "1", required = false) int limit,
			@RequestParam(defaultValue = "id,desc", required = false) String sort) {
		Page<PostDTO> post = postService.findAll(page, limit, sort);
		System.out.println(post.toString());
		return new ResponseEntity<Page<PostDTO>>(post, HttpStatus.ACCEPTED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOneById(@PathVariable int id) {
		PostDTO post;
		try {
			post = postService.findOneById(id);
			return new ResponseEntity<PostDTO>(post, HttpStatus.ACCEPTED);
		} catch (PostNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@GetMapping("/action")
	public ResponseEntity<?> findAllByUser_IdAndActions_Name(@RequestBody ActionDTO action) {
		Pageable pageable = null;
		Page<PostDTO> l = postService.findAllByUser_IdAndActions_Name(action.getIdUser(), action.getName().name(),
				pageable);
		return new ResponseEntity<Page<PostDTO>>(l, HttpStatus.ACCEPTED);
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody PostDTO post, BindingResult bindingResult) {
		ResponseEntity<?> error = validationBindingResult.process(bindingResult);
		if (error != null) {
			return error;
		}
		postService.save(post);
		return ResponseEntity.ok("success");
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody PostDTO post, BindingResult bindingResult,
			@PathVariable int id) {
		try {
			ResponseEntity<?> error = validationBindingResult.process(bindingResult);
			if (error != null) {
				return error;
			}
			postService.update(post, id);
			return ResponseEntity.ok("success");
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody int[] id) {
		postService.delete(id);
		return ResponseEntity.ok("success");
	}

	@GetMapping("/state")
	public ResponseEntity<?> findAllByState(@RequestParam String state,
			@PageableDefault(page = 0, size = 5) Pageable p) {
		System.out.println(state);
		Page<PostDTO> post = postService.findAllByState(state, p);
		return new ResponseEntity<Page<PostDTO>>(post, HttpStatus.ACCEPTED);
	}
}
