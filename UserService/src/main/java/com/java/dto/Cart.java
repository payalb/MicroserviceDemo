package com.java.dto;

import java.util.List;

import javax.persistence.OneToMany;

public class Cart {
	private long userId;
	@OneToMany
	private List<Product> products;
}
