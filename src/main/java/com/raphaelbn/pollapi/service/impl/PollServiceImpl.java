package com.raphaelbn.pollapi.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.raphaelbn.pollapi.entity.PollEntity;
import com.raphaelbn.pollapi.entity.VoteEntity;
import com.raphaelbn.pollapi.entity.dto.PollResultDTO;
import com.raphaelbn.pollapi.repository.PollRepository;
import com.raphaelbn.pollapi.service.PollService;
import com.raphaelbn.pollapi.service.VoteService;

@Service
public class PollServiceImpl implements PollService {

	private PollRepository pollRepository;

	private VoteService voteService;

	public PollServiceImpl(PollRepository pollRepository, VoteService voteService) {
		this.pollRepository = pollRepository;
		this.voteService = voteService;
	}

	public PollEntity save(PollEntity poll) {
		return this.pollRepository.save(poll);
	}

	public PollEntity getById(Long id) {
		try {
			return this.pollRepository.getPollById(id).orElseThrow();
		} catch (NoSuchElementException e) {
			throw new EntityNotFoundException("Pauta não encontrada!");
		}
	}

	public List<PollEntity> getValidPolls() {
		return pollRepository.findAll().stream()
				.filter(poll -> LocalDateTime.now().isBefore(poll.getStartedDate().plusMinutes(poll.getDurationTime())))
				.collect(Collectors.toList());
	}

	public PollEntity getByIdIfValid(Long id) {
		PollEntity poll = getById(id);
		if (poll.getStartedDate().plusMinutes(poll.getDurationTime()).isBefore(LocalDateTime.now())) {
			throw new EntityNotFoundException("Pauta não encontrada!");
		}
		return poll;
	}

	public PollResultDTO getResult(Long id) {
		PollEntity poll = getById(id);
		PollResultDTO resultDTO = poll.builResultDTO();
		return resultDTO;
	}

	public VoteEntity vote(Long id, VoteEntity vote) {
		PollEntity poll = getByIdIfValid(id);
		VoteEntity voteCreated = this.voteService.save(vote);
		poll.getVotes().add(voteCreated);
		save(poll);
		return voteCreated;
	}
}
