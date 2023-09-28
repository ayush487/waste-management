package com.ayushtech.wastemanagement.exception;

import java.time.LocalDateTime;
import java.util.List;

public class MethodArguementErrorDetails {

	private LocalDateTime dateTime;
	private List<String> messages;
	private int errorCount;

	public MethodArguementErrorDetails(LocalDateTime dateTime, List<String> messages, int errorCount) {
		super();
		this.dateTime = dateTime;
		this.messages = messages;
		this.errorCount = errorCount;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public List<String> getMessage() {
		return messages;
	}

	public int getErrorCount() {
		return errorCount;
	}

}
