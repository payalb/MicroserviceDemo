package com.java.dto;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Patient {
	@Id
	private String username;
	private String firstName;
	private String lastName;
	private String emailId;
	private long phoneNumber;
	private LocalDate dob;
	
	
	@ElementCollection(fetch=FetchType.EAGER)
	private List<File> medicalRecords= new ArrayList<>();

}

//medical history