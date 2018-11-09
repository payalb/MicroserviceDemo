package com.java.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {
	@Id
	@GeneratedValue
	private long orderId;
	private LocalDateTime orderDate;
	@ElementCollection
	@Embedded
	private List<Product> productDetails;
	private Status orderStatus;
	private float mrp;
	private float discountPercentage;
	@Transient
	private float totalPrice;
	@NotNull
	private String primaryAddress;
}
