package com.neo.ticketingapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.neo.ticketingapp.model.RoguePassenger;

public interface RoguePassengerRepository extends MongoRepository<RoguePassenger, String> {
	List<RoguePassenger> findByRougePassenngerId(String rougePassenngerId);
}
