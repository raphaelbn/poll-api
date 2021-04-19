package com.raphaelbn.pollapi.exception;

public class ApiResponseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApiResponseException(String message) {
		super(message);
	}

	public ApiResponseException(String message, Throwable throwable) {
		super(message, throwable);
	}
}