package com.raphaelbn.pollapi.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.raphaelbn.pollapi.entity.PollEntity;
import com.raphaelbn.pollapi.entity.VoteEntity;
import com.raphaelbn.pollapi.entity.dto.PollResultDTO;

@Component
public interface PollService {

	public PollEntity save(PollEntity poll);

	public PollEntity getById(Long id);

	public List<PollEntity> getValidPolls();

	public PollEntity getByIdIfValid(Long id);

	public PollResultDTO getResult(Long id);

	public VoteEntity vote(Long id, VoteEntity vote);
}
