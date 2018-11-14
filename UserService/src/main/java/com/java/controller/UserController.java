/*package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.java.dao.UserRepository;
import com.java.dto.Cart;

@BasePathAwareController
public class UserController {
	
	@Autowired UserRepository rep;

	@GetMapping("users/{username}/cart")
	public Cart getCartByUsername(@PathVariable("username") String username) {
		return rep.getCartByUsername(username);
	}
}
*/