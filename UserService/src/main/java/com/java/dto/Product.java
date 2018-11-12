package com.java.dto;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Embeddable
public class Product {

	private String name;
	private float price;
	private int quantity;
	
}
