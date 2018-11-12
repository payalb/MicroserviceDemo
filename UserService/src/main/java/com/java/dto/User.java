package com.java.dto;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	@Id
	private String username;
	private String password;
	@ElementCollection
	private List<String> roles;
	@Embedded
	private List<Address> address;
	@Embedded
	private List<CardDetails> card;
	@Embedded
	private Cart cart;
}
