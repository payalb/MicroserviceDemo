package com.java.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.java.dto.User;

public class UserSerializer extends JsonSerializer<User> {

	@Override
	public void serialize(User value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		//gen.writeStartObject();
		gen.writeStringField("username", value.getUsername());
		//gen.writeEndObject();
	}

}
