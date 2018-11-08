package com.java.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.java.dto.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	
	@RestResource(path="appointmentId")
	List<Appointment> findByAppointmentId(@Param("appointmentId")int appointmentId);
	
	@RestResource(path="patientId")
	 Set<Appointment> findByPatientId(int  patientId);
	
	
	
}
