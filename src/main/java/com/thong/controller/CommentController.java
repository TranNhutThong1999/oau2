package com.thong.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.thong.dto.CommentDTO;
import com.thong.exception.ValidationBindingResult;
import com.thong.service.ICommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
	@Autowired
	private ICommentService commentService;
	
	@Autowired
	private ValidationBindingResult validationBindingResult;
	
	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "1", required = false) int limit,
			@RequestParam(defaultValue = "id,desc", required = false) String sort) {
		Page<CommentDTO> cm = commentService.findAll(page, limit, sort);
		return new ResponseEntity<Page<CommentDTO>>(cm, HttpStatus.ACCEPTED);
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody int[] id) {
		commentService.delete(id);
		return ResponseEntity.ok("success");
	}
	
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody CommentDTO m, BindingResult bindingResult) {
		ResponseEntity<?> error = validationBindingResult.process(bindingResult);
		if (error != null) {
			return error;
		}
		commentService.save(m);
		return ResponseEntity.ok("success");
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody CommentDTO m, BindingResult bindingResult, @PathVariable int id) {
		ResponseEntity<?> error = validationBindingResult.process(bindingResult);
		if (error != null) {
			return error;
		}
		commentService.update(m,id);
		return ResponseEntity.ok("success");
	}
}
