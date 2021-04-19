package com.raphaelbn.pollapi.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.raphaelbn.pollapi.entity.UserEntity;
import com.raphaelbn.pollapi.exception.ApiResponseException;
import com.raphaelbn.pollapi.repository.UserRepository;
import com.raphaelbn.pollapi.service.UserService;
import com.raphaelbn.pollapi.service.integration.JsonBodyHandler;
import com.raphaelbn.pollapi.service.integration.UserValidationResponse;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Value("${url.validate.user.cpf}")
	private String userValidateUserCpf;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserEntity save(UserEntity user) {
		try {
			HttpClient client = HttpClient.newHttpClient();

			HttpRequest request = HttpRequest.newBuilder(URI.create(userValidateUserCpf.concat(user.getCpf()))).build();

			HttpResponse<Supplier<UserValidationResponse>> response = client.send(request,
					new JsonBodyHandler<>(UserValidationResponse.class));

			if (response.statusCode() == 200 && "ABLE_TO_VOTE".equals(response.body().get().getStatus())) {
				return userRepository.save(user);
			} else {
				throw new ApiResponseException("Usuário não pode votar!");
			}
		} catch (IOException | InterruptedException e) {
			throw new ApiResponseException("Não foi possível validar informações do usuário, favor tentar mais tarde!");
		}
	}

	public UserEntity getByCpf(String cpf) {
		try {
			return userRepository.findUserByCpf(cpf).orElseThrow();
		} catch (NoSuchElementException e) {
			throw new EntityNotFoundException("Usuário não encontrada!");
		}
	}
}
