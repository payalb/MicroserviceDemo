package com.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

//@EnableEurekaClient
@SpringBootApplication
@EntityScan(basePackages="com.java.dto")
@EnableSpringDataWebSupport
public class MyStarter {

	public static void main(String[] args) {
		SpringApplication.run(MyStarter.class, args);
	}

	/*@Bean
	public Jackson2ObjectMapperBuilderCustomizer bean() {
		Jackson2ObjectMapperBuilderCustomizer bean= new Jackson2ObjectMapperBuilderCustomizer() {

			@Override
			public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
				jacksonObjectMapperBuilder.serializerByType(CardDetails.class, new CardSerializer());
				jacksonObjectMapperBuilder.deserializerByType(CardDetails.class, new CardDeserializer());
			}
			
		};
		return bean;
	}*/
	

	/*@Bean
	public ObjectMapper mapper() {
		return Jackson2ObjectMapperBuilder.json().featuresToEnable(MapperFeature.DEFAULT_VIEW_INCLUSION).build();
	}*/
}
