package com.raphaelbn.pollapi.exception;

import java.sql.SQLException;
import java.util.Date;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = { ApiResponseException.class })
	public ResponseEntity<Object> handleApiRequestException(ApiResponseException e) {
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		ApiException exception = new ApiException(e.getMessage(), badRequest, new Date());

		return new ResponseEntity<>(exception, badRequest);
	}

	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		ApiException exception = new ApiException(e.getMessage(), badRequest, new Date());

		return new ResponseEntity<>(exception, badRequest);
	}

	@ExceptionHandler(value = { DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		ApiException exception = new ApiException(e.getMessage(), badRequest, new Date());

		return new ResponseEntity<>(exception, badRequest);
	}

	@ExceptionHandler(value = { SQLException.class })
	public ResponseEntity<Object> handleSQLException(Exception e) {
		HttpStatus internalServer = HttpStatus.INTERNAL_SERVER_ERROR;
		ApiException exception = new ApiException(e.getMessage(), internalServer, new Date());

		return new ResponseEntity<>(exception, internalServer);
	}

	@ExceptionHandler(value = { ClassNotFoundException.class })
	public ResponseEntity<Object> handleClassNotFoundException(ClassNotFoundException e) {
		HttpStatus notFound = HttpStatus.NOT_FOUND;
		ApiException exception = new ApiException(e.getMessage(), notFound, new Date());

		return new ResponseEntity<>(exception, notFound);
	}

	@ExceptionHandler(value = { EntityNotFoundException.class })
	public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {
		HttpStatus notFound = HttpStatus.NOT_FOUND;
		ApiException exception = new ApiException(e.getMessage(), notFound, new Date());

		return new ResponseEntity<>(exception, notFound);
	}
}