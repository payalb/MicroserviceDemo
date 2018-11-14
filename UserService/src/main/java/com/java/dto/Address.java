package com.java.dto;

import javax.persistence.Embeddable;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamicUpdate
@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	private int houseNumber;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String country;
	private int zipcode;
}
