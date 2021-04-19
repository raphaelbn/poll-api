package com.raphaelbn.pollapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raphaelbn.pollapi.entity.UserEntity;
import com.raphaelbn.pollapi.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{cpf}")
	public ResponseEntity<UserEntity> getByCpf(@PathVariable("cpf") String cpf) {
		UserEntity user = this.userService.getByCpf(cpf);
		return ResponseEntity.ok(user);
	}

	@PostMapping
	public ResponseEntity<UserEntity> save(@RequestBody UserEntity user) {
		UserEntity userCreated = this.userService.save(user);
		return ResponseEntity.ok(userCreated);
	}
}
