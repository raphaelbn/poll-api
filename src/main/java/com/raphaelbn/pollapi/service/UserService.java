package com.raphaelbn.pollapi.service;

import org.springframework.stereotype.Component;

import com.raphaelbn.pollapi.entity.UserEntity;

@Component
public interface UserService {

	public UserEntity save(UserEntity user);

	public UserEntity getByCpf(String cpf);
}
