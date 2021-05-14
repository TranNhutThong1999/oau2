package com.thong.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.thong.dto.ImageDTO;
import com.thong.exception.PostNotFoundException;
import com.thong.service.IImageService;

@RestController
public class ImageController {
	@Autowired
	private IImageService imageService;

	@PostMapping("upload/{idPost}")
	public ResponseEntity<?> upload(@RequestParam MultipartFile[] files, @PathVariable int idPost) throws IOException {

		Arrays.asList(files).stream().forEach(file -> {
			try {
				imageService.save(idPost, files[0]);
			} catch (IOException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			}
		});

		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

	@GetMapping("/image/{idPost}")
	public ResponseEntity<ByteArrayResource> findAllByPost(@PathVariable int idPost) throws IOException {
		try {
			List<ImageDTO> i = imageService.findByPostId(idPost);
			Path file = Paths.get("uploads", i.get(0).getLink());
			byte[] buffer = Files.readAllBytes(file);
			ByteArrayResource b = new ByteArrayResource(buffer);
			return ResponseEntity.ok().contentLength(buffer.length).contentType(MediaType.parseMediaType("image/png"))
					.body(b);
		} catch (PostNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}
	@DeleteMapping("/image")
	public ResponseEntity<?> delete(@RequestBody int[] id){
		try {
			imageService.delete(id);
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
}
