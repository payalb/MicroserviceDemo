package com.java.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Table(name = "ADDRESS_TABLE")
@DynamicUpdate
@Data
@Entity
public class Address {
	@Id
	private long addressId;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String country;
	private int zipcode;
}
