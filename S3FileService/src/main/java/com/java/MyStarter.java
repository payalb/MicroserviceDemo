package com.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableConfigurationProperties(S3Properties.class)
//@EnableEurekaClient
public class MyStarter {

	public static void main(String[] args) {
		SpringApplication.run(MyStarter.class, args);

	}

}
