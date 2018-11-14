package com.java.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.java.dto.User;

public class UserDeserializer extends JsonDeserializer <User>{

	@Override
	public User deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String username=p.getValueAsString();
		User obj= new User();
		obj.setUsername(username);
		return obj;
	}

}
