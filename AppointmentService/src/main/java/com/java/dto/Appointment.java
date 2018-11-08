package com.java.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName="appointment")
@Data
@Entity
@SQLDelete(sql="update Appointment set status = 'Cancelled' where appointmentId = ?")
public class Appointment implements Serializable{
	private static final long serialVersionUID = 1342910184120806871L;
	@Id
	@GeneratedValue
	@JsonIgnore
	int appointmentId;
	@Future
	private LocalDateTime dateOfAppointment;
	@NotEmpty
	private String patientEmailId;
	@NotEmpty
	private String clinicAddress;
	@NotEmpty
	private String doctorEmailId; 
	@NotEmpty
	private String doctorName;
	@NotEmpty
	@Builder.Default
	private String speciality="General Physician";
	@Builder.Default
	@Enumerated(EnumType.STRING)
	private AppointmentStatus status=AppointmentStatus.BOOKED; 
}

//medical history