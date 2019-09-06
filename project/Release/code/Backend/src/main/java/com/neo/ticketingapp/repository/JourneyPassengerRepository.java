package com.neo.ticketingapp.repository;

import com.neo.ticketingapp.model.JourneyPassenger;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JourneyPassengerRepository extends MongoRepository<JourneyPassenger,String> {
    List<JourneyPassenger> findByJourneyID(String journeyID);
}
