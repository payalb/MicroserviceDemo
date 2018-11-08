package com.java.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Login {
	@Id
	private String username;
	private String password;
	private List<String> roles;
	
}

//medical history