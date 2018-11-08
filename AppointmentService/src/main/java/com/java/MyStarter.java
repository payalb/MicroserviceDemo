package com.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.java.dao")
@EntityScan(basePackages="com.java.dto")
public class MyStarter {

	public static void main(String[] args) {
		SpringApplication.run(MyStarter.class, args);
//		Appointment appointment= Appointment.appointment().doctorName("kapil").clinicAddress("").build();
	}

}
