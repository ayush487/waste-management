package com.ayushtech.wastemanagement.exception;

import java.time.LocalDateTime;

public class CustomErrorDetails extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LocalDateTime dateTime;
	private String message;
	
	public CustomErrorDetails(LocalDateTime dateTime, String message) {
		super();
		this.dateTime = dateTime;
		this.message = message;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public String getMessage() {
		return message;
	}
	
}
