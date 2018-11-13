package com.java;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.java.dao")
@EntityScan(basePackages="com.java.dto")
public class MyStarter {

	public static void main(String[] args) {
		SpringApplication.run(MyStarter.class, args);

	}
	
	@Bean
	public LocaleResolver getLocaleResolver() {
		SessionLocaleResolver resolver= new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.US);
		return resolver;
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource ms= new ResourceBundleMessageSource();
		ms.addBasenames("message");
		return ms;
	}

}
