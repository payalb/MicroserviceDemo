package com.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.java.dao")
@EntityScan(basePackages="com.java.dto")
@EnableEurekaClient
@EnableCircuitBreaker
public class MyStarter {

	public static void main(String[] args) {
		SpringApplication.run(MyStarter.class, args);

	}
	
	@LoadBalanced
	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}

}
