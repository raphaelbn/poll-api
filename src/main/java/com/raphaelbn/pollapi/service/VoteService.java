package com.raphaelbn.pollapi.service;

import org.springframework.stereotype.Component;

import com.raphaelbn.pollapi.entity.VoteEntity;

@Component
public interface VoteService {

	public VoteEntity save(VoteEntity vote);
}
