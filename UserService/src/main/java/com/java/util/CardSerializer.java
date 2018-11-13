/*package com.java.util;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.java.dto.CardDetails;

@JsonComponent
public class CardSerializer extends JsonSerializer<CardDetails>{

	private static final long serialVersionUID = -1924263843301475188L;

	public CardSerializer() {
		super(CardDetails.class);
	}

	@Override
	public void serialize(CardDetails value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("cardNumber", value.getCardNumber());
		gen.writeStringField("name",value.getName());
		gen.writeStringField("issueDate",value.getIssueDate().toString());
		gen.writeStringField("type",value.getType().name	());
		gen.writeStringField("bankName",value.getBankName());
		gen.writeEndObject();
	}

}
*/