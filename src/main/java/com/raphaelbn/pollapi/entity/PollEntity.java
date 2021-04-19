package com.raphaelbn.pollapi.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.raphaelbn.pollapi.entity.dto.PollResultDTO;

import lombok.Data;

@Data
@Entity(name = "poll")
public class PollEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O assunto da pauta é obrigatorio.")
	private String subject;

	@NotBlank(message = "O conteudo da pauta é obrigatorio.")
	private String content;

	@JsonIgnore
	@OneToMany
	private Set<VoteEntity> votes;
	
	private LocalDateTime startedDate = LocalDateTime.now();

	private Long durationTime = 1L;

	public PollResultDTO builResultDTO() {
		PollResultDTO resultDTO = new PollResultDTO();
		resultDTO.setPollId(this.id);
		resultDTO.setFavorVotes(this.votes.stream().filter(vote -> vote.isFavor()).count());
		resultDTO.setAgainstVotes(this.votes.stream().filter(vote -> !vote.isFavor()).count());
		return resultDTO;
	}
}
