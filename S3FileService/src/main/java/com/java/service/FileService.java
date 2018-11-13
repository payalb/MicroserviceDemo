package com.java.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

public class FileService {
	@Value("${bucketName}")
	String bucketName;
	@Autowired
	AmazonS3 s3Client;
	@Autowired
	ResourceLoader loader;

	public boolean saveFile(String filename, MultipartFile file) {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(file.getSize());
		try {
			s3Client.putObject(bucketName, filename, file.getInputStream(), metadata);
			return true;
		} catch (SdkClientException | IOException e) {
			return false;
		}

	}

	public void deleteFile(String filename) {
		s3Client.deleteObject(bucketName, filename);
	}

	public Resource getFile(String filename) {
		if (filename.startsWith("s3://")) {
			return loader.getResource(bucketName + "/" + filename);
		} else {
			return loader.getResource("s3://" + bucketName + "/" + filename);
		}
	}
}
