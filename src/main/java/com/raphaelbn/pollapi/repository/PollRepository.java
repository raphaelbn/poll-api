package com.raphaelbn.pollapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raphaelbn.pollapi.entity.PollEntity;

@Repository
public interface PollRepository extends JpaRepository<PollEntity, Long> {

	Optional<PollEntity> getPollById(Long id);
}
