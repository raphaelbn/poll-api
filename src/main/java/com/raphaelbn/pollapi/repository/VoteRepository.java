package com.raphaelbn.pollapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raphaelbn.pollapi.entity.VoteEntity;

public interface VoteRepository extends JpaRepository<VoteEntity, Long> {

}
