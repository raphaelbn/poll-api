package com.raphaelbn.pollapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raphaelbn.pollapi.entity.PollEntity;
import com.raphaelbn.pollapi.entity.VoteEntity;
import com.raphaelbn.pollapi.entity.dto.PollResultDTO;
import com.raphaelbn.pollapi.service.PollService;
import com.raphaelbn.pollapi.service.VoteService;

@RestController
@RequestMapping("/api/poll")
public class PollController {

	private PollService pollService;

	public PollController(PollService pollService) {
		this.pollService = pollService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<PollEntity> getById(@PathVariable("id") Long id) {
		PollEntity poll = this.pollService.getById(id);
		return ResponseEntity.ok(poll);
	}

	@GetMapping("/{id}/result")
	public ResponseEntity<PollResultDTO> getResult(@PathVariable("id") Long id) {
		PollResultDTO pollResult = this.pollService.getResult(id);
		return ResponseEntity.ok(pollResult);
	}

	@GetMapping
	public ResponseEntity<List<PollEntity>> getValidPolls() {
		List<PollEntity> validPolls = this.pollService.getValidPolls();
		return ResponseEntity.ok(validPolls);
	}

	@PostMapping
	public ResponseEntity<PollEntity> save(@RequestBody PollEntity poll) {
		PollEntity pollCreated = this.pollService.save(poll);
		return ResponseEntity.ok(pollCreated);
	}

	@PostMapping("/{id}/vote")
	public ResponseEntity<VoteEntity> save(@PathVariable("id") Long id, @RequestBody VoteEntity vote) {
		VoteEntity voteCreated = this.pollService.vote(id, vote);
		return ResponseEntity.ok(voteCreated);
	}
}
