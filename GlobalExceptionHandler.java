package com.restful.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	// Exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException rx, WebRequest wb) {

		ErrorDetails errordetails = new ErrorDetails(new Date(), rx.getMessage(), wb.getDescription(false));

		return new ResponseEntity<>(errordetails, HttpStatus.NOT_FOUND);

	}

	// globalException
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalException(Exception rx, WebRequest wb) {

		ErrorDetails errordetails = new ErrorDetails(new Date(), rx.getMessage(), wb.getDescription(false));

		return new ResponseEntity<>(errordetails, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	//
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest webrequest) {

		ErrorDetails errordetails = new ErrorDetails(new Date(), "Validation Failed", ex.getBindingResult().getFieldError().getDefaultMessage().toUpperCase());
		return new ResponseEntity<>(errordetails, HttpStatus.BAD_REQUEST);

	}

}
