package com.thong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.thong.dto.ActionDTO;
import com.thong.service.IActionService;

@RestController
@RequestMapping("/actions")
public class ActionController {
	@Autowired
	private IActionService actionService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody ActionDTO action) {
		try {
			actionService.save(action);
			return ResponseEntity.ok("success");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	
	}
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody int id) {
		actionService.delete(id);
		return ResponseEntity.ok("success");
	}
}
