package com.java.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.java.S3Properties;
@Service
public class FileService {
	/*The @ConfigurationProperties classes are regular spring beans, and you can inject them in the same way as any other bean.*/
	@Autowired private S3Properties properties;
	@Autowired
	@Qualifier("client")
	AmazonS3Client s3Client;
	@Autowired
	ResourceLoader loader;

	public boolean saveFile(String filename, MultipartFile file) {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(file.getSize());
		try {
			s3Client.putObject(properties.getBucketName(), filename, file.getInputStream(), metadata);
			return true;
		} catch (SdkClientException | IOException e) {
			return false;
		}

	}

	public void deleteFile(String filename) {
		s3Client.deleteObject(properties.getBucketName(), filename);
	}

	public Resource getFile(String filename) {
		if (filename.startsWith("s3://")) {
			return loader.getResource(properties.getBucketName() + "/" + filename);
		} else {
			return loader.getResource("s3://" + properties.getBucketName() + "/" + filename);
		}
	}
}
