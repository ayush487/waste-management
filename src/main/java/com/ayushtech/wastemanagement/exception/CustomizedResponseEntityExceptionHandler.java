package com.ayushtech.wastemanagement.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmailAlreadyRegisteredException.class)
	public final ResponseEntity<CustomErrorDetails> handleEmailAlreadyRegistered(Exception ex, WebRequest request) {
		CustomErrorDetails errorDetails = new CustomErrorDetails(
					LocalDateTime.now(),
					ex.getMessage()
				);
		return ResponseEntity.badRequest().body(errorDetails);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		MethodArguementErrorDetails errorDetails = new MethodArguementErrorDetails(
					LocalDateTime.now(),
					ex.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList(),
					ex.getErrorCount()
				);
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}

}
