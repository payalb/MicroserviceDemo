package com.java.dto;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="user_info")
//@JsonIgnoreProperties(value= {"password", "card"})
public class User {
	@Id
	private String username;
//	@JsonIgnore
	private String password;
//	@JsonIgnore
	@ElementCollection
	private List<String> roles;
	@Embedded
	private List<Address> address;
	//bytes: @Lob
	//s3://bucket-demo/doc1.jpg
	private String pictureUrl;
	@Embedded
//	@JsonIgnore
	private List<CardDetails> card;
	@Embedded
	private Cart cart;
}
