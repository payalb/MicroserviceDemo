package com.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.dto.Cart;
import com.java.dto.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	@Query("select cart from User where username= ?1")
	public Cart getCartByUsername(String username);
	
}
