package com.raphaelbn.pollapi.service.integration;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class UserValidationResponse {
	private final String status;

	public UserValidationResponse(@JsonProperty("status") String status) {
		this.status = status;
	}
}