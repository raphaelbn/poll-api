package com.raphaelbn.pollapi.entity.dto;

import lombok.Data;

@Data
public class PollResultDTO {

	private Long pollId;

	private Long favorVotes;

	private Long againstVotes;

	public String getResult() {
		return this.favorVotes > this.againstVotes ? "Aprovada"
				: (this.againstVotes < this.favorVotes ? "Reprovada" : "Empate");
	}
}
