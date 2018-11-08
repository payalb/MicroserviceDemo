package com.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.dto.Login;
public interface LoginRepository extends JpaRepository<Login, Integer> {
	
}
