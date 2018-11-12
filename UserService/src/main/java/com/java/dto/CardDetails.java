package com.java.dto;

import java.time.LocalDate;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDetails {
	private long cardNumber;
	private String name;
	private LocalDate issueDate;
	private LocalDate expiryDate;
	private int cvv;
	private CardType type;
	private String bankName;
}
