package com.java.dto;

import java.time.LocalDate;

import javax.persistence.Embeddable;

import org.springframework.http.converter.json.MappingJacksonValue;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
//@JsonFilter("protectedFilter")
@Builder(buildMethodName="build")
//@JsonSerialize(using=CardSerializer.class)
//@JsonDeserialize(as=CardDeserializer.class)
@AllArgsConstructor
public class CardDetails {
	private long cardNumber;
	private String name;
	private LocalDate issueDate;
	private LocalDate expiryDate;
	private int cvv;
	private CardType type;
	private String bankName;
	
	
	
	public static CardDetails getDefaultCard() {
		return new CardDetails(1324_6648_6446_7644l, "Payal Bansal", LocalDate.now(), LocalDate.now().plusYears(10), 768, CardType.DEBIT, "Hdfc");
	}
	
	public static MappingJacksonValue getFilter(CardDetails card, SimpleBeanPropertyFilter filter) {
		MappingJacksonValue data= new MappingJacksonValue(card);
		FilterProvider provider= new SimpleFilterProvider().addFilter("protectedFilter", filter);
		data.setFilters(provider);
		return data;
	}

}
