package com.raphaelbn.pollapi.service.impl;

import org.springframework.stereotype.Service;

import com.raphaelbn.pollapi.entity.VoteEntity;
import com.raphaelbn.pollapi.repository.VoteRepository;
import com.raphaelbn.pollapi.service.VoteService;

@Service
public class VoteServiceImpl implements VoteService {

	private VoteRepository voteRepository;

	public VoteServiceImpl(VoteRepository voteRepository) {
		this.voteRepository = voteRepository;
	}

	public VoteEntity save(VoteEntity vote) {
		VoteEntity coteSaved = this.voteRepository.save(vote);
		return coteSaved;
	}
}
