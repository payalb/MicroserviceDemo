package com.java.util;

import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.GenericMessage;

import com.java.dto.Appointment;

@RepositoryEventHandler(Appointment.class)
public class BookingEventHandler {
	/*
	 * https://www.baeldung.com/spring-data-rest-events
	 */
	@HandleAfterCreate
	 @HandleAfterDelete
	 @HandleAfterSave
	@SendTo("appointment-queue")
	public Message<Appointment> handleAppointmentAfterCreate(Appointment appointment) {
		 GenericMessage<Appointment> message= new GenericMessage<Appointment>(appointment);
		 return message;
	}
	
}

//Notification service: mails to the recipients| doctor & patient| mail ids
