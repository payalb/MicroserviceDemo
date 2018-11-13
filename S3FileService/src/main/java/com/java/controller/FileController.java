package com.java.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.java.service.FileService;

@RestController("/files")
public class FileController {

	@Autowired
	FileService service;

	@PostMapping
	public ResponseEntity saveFile(@RequestParam("filename") String name, @RequestParam("file") MultipartFile file) {

		boolean success = service.saveFile(name, file);
		if (success) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	@PostMapping
	public ResponseEntity getFile(@RequestParam("filename") String name) {

		Resource resource = service.getFile(name);
		try {
			return ResponseEntity.ok().contentLength(resource.contentLength()).contentType(MediaType.IMAGE_JPEG)
					.body(new InputStreamResource(resource.getInputStream()));
		} catch (IOException e) {
			return ResponseEntity.badRequest().body("Could not retrieve the file");
		}

	}
}
