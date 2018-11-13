package com.java;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/*If any of these validations fail then the main application would fail to start with an IllegalStateException till the incorrect property is corrected.
 *  it is important that we declare getters and setters for each of the properties as they’re used by the validator framework to access the concerned properties.
 *  All the kebab case property names (ex: upload-dir) are bound to the corresponding camel case fields (ex: uploadDir) in the POJO class.

Note that the properties can also be specified in camel case. But using kebab case is recommended.
To enable validation, you just need to add Spring’s @Validated annotation to the @ConfigurationProperties class 
he Spring Boot application will throw a validation exception on startup, if validation fails*/
/*public @interface NestedConfigurationProperty
Indicates that a field in a ConfigurationProperties object should be treated as if it were a nested type. This annotation has no bearing on the actual binding processes, but it is used by the spring-boot-configuration-processor as a hint that a field is not bound as a single value. When this is specified, a nested group is created for the field and its type is harvested.
*///@Configuration
@PropertySource(value="classpath:s3.properties")
@Component
@Validated
@ConfigurationProperties(ignoreUnknownFields = false, prefix="s3")
public class S3Properties {
	@NotBlank
	private String bucketName;
	@NotNull
	@NestedConfigurationProperty
	private AWS aws= new AWS();

	static class AWS {
		@NotBlank
		private String accessKeyId;
		@NotBlank
		private String accessKeySecret;

		public String getAccessKeyId() {
			return accessKeyId;
		}

		public void setAccessKeyId(String accessKeyId) {
			this.accessKeyId = accessKeyId;
		}

		public String getAccessKeySecret() {
			return accessKeySecret;
		}

		public void setAccessKeySecret(String accessKeySecret) {
			this.accessKeySecret = accessKeySecret;
		}
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public AWS getAws() {
		return aws;
	}

	public void setAws(AWS aws) {
		this.aws = aws;
	}
}
