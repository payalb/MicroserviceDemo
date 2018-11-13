package com.java;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.http.MediaType;

@Configuration
public class MyConfigurerAdapter extends RepositoryRestConfigurerAdapter{

	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.setBasePath("/api/v1");
		config.setDefaultMediaType(MediaType.APPLICATION_JSON);
		config.setDefaultPageSize(50);
	//	config.returnBodyOnCreate("returnBody");
		//config.returnBodyOnUpdate("returnBody");
	}

}
