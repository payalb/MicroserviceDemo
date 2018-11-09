package com.java.dto;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDetails {
	@Id
	private long cardNumber;
	private String name;
	private LocalDate issueDate;
	private LocalDate expiryDate;
	private int cvv;
	private CardType type;
	private String bankName;
}
