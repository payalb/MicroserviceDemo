package com.java.dto;

import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class Cart {
	private long userId;
	@Embedded
	private List<Product> products;
}
