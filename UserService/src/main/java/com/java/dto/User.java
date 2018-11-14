package com.java.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class User {
	@JsonProperty(required=true)
	@Id
	private String username;
	private String password;
	@ElementCollection
	private List<String> roles= new ArrayList<>();
	@Embedded
	@ElementCollection
	private List<Address> address= new ArrayList<>();
	private String pictureUrl;
	@Embedded
	@ElementCollection
	private List<CardDetails> card= new ArrayList<>();
	@Embedded
	private Cart cart;
}
