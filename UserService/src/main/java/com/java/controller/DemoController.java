/*package com.java.controller;

import java.time.LocalDate;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.java.dto.CardDetails;
import com.java.dto.CardType;

@RestController
@RequestMapping(produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class DemoController {

	@Autowired MessageSource source;
	
	@GetMapping("data1")
	public String data(@RequestHeader(name="Accept-Language", defaultValue="en") Locale locale) {
		return source.getMessage("message", null, locale);
	}
	

	@GetMapping("data2")
	public String data2( Locale locale) {
		return source.getMessage("message", null, locale);
	}
	
	
	
	@GetMapping("data3")
	public String data3() {
		return source.getMessage("message", null, LocaleContextHolder.getLocale());
	}
	
	//parameter versioning: Amazon
	@GetMapping(path="/data", params="version=1")
	public MappingJacksonValue version1() {
		return getData();
	}
	
	@GetMapping(path="/data", params="version=2")
	public MappingJacksonValue version2() {
		return getData2();
	}
	
	//header versioning: Microsoft
	@GetMapping(path="/data", headers="X-API-VERSION=1")
	public MappingJacksonValue version3() {
		return getData();
	}
	
	@GetMapping(path="/data", headers="X-API-VERSION=2")
	public MappingJacksonValue version4() {
		return getData2();
	}
	
	//MEDIATYPE VERSIONING: Github
	@GetMapping(path="/data", produces= {"application/api.v1+json", "application/api.v1+xml"})
	public MappingJacksonValue version5() {
		return getData();
	}
	
	@GetMapping(path="/data", produces= {"application/api.v2+json","application/api.v2+xml","application/xml","application/json"})
	public MappingJacksonValue version6() {
		return getData2();
	}
	
	@GetMapping(path="/data-json")
	public CardDetails serializer() {
		return CardDetails.getDefaultCard();
	}
	
	
	@PostMapping(path="/data-json")
	public CardDetails deserializer(CardDetails details) {
		return details;
	}
	
	
	@GetMapping("dynamic-filtering")
	public MappingJacksonValue getData() {
		MappingJacksonValue data = CardDetails.getFilter(CardDetails.getDefaultCard(),SimpleBeanPropertyFilter.filterOutAllExcept("cardNumber","type"));
		return data;
	}

	
	
	@GetMapping("dynamic-filtering2")
	public MappingJacksonValue getData2() {
		SimpleBeanPropertyFilter filter=  SimpleBeanPropertyFilter.serializeAll();
		MappingJacksonValue data= new MappingJacksonValue(new CardDetails(1324_6648_6446_7644l, "Payal Bansal", LocalDate.now(), LocalDate.now().plusYears(10), 768, CardType.DEBIT, "Hdfc"));
		FilterProvider provider= new SimpleFilterProvider().addFilter("protectedFilter", filter);
		data.setFilters(provider);
		return data;
	}
	
}
*/