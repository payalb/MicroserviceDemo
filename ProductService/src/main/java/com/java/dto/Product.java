package com.java.dto;

import javax.persistence.Entity;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Data
@Entity
@DynamicUpdate
public class Product {
	private long productId;
	private String productName;
	private String imageUrl;
	private float unitPrice;
	private int productRating;
	private int stockQuantity;
	private String productDescription;
	private float discountPercentage;
}

//medical history