package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.java.exception.CartModificationException;

@RestController
@RequestMapping("/users/{userId}/orders")
public class OrderController {

	@Autowired RestTemplate template;
	@PostMapping
	public void addOrder(@PathVariable("userId")int userId, HttpHeaders header) {
		//TODO add order to db
		
		
		//Set cart to be empty
		
		HttpEntity<String> entity= new HttpEntity<String>(header);
		ResponseEntity<Void> response=template.exchange("http://localhost:<port>/users/"+ userId+ "/cart", HttpMethod.DELETE, entity, Void.class  );
		if(response.getStatusCode()!= HttpStatus.NO_CONTENT) {
			throw new CartModificationException("Unable to set cart to empty");
		}
	}
} 
