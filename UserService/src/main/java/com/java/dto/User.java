package com.java.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity 
public class User {
	@Id
	@OneToOne
	private Login loginDetails;
	@OneToMany
	private List<Address> address;
	@ManyToMany
	private List<CardDetails> card;
	@OneToOne
	private Cart cart;
}
