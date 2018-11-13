/*package com.java.util;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.java.dto.CardDetails;
import com.java.dto.CardType;

@JsonComponent
public class CardDeserializer extends JsonObjectDeserializer<CardDetails>{

	private static final long serialVersionUID = 4462669851462459659L;

	public CardDeserializer() {
		super(CardDetails.class);
	}

	{
		System.out.println("des");
	}
	@Override
	protected CardDetails deserializeObject(JsonParser p, DeserializationContext context, ObjectCodec codec,
			JsonNode tree) throws IOException {
		System.out.println("deserializer");
	//	tree.get("cardNumber")
		CardDetails obj= CardDetails.builder().cardNumber(p.getLongValue())
				.name(p.getValueAsString()).issueDate(LocalDate.parse(p.getValueAsString())).type(CardType.valueOf(p.getValueAsString()))
				.bankName(p.getValueAsString()).build();
		return obj;
	}

}
*/