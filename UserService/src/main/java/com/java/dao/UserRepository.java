package com.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.dto.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	/*@Query("select cart from User where username= :username")
	public Cart getCartByUsername(@Param("username")String username);
	*/
}
