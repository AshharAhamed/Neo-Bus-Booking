package com.neo.ticketingapp.repository;

import com.neo.ticketingapp.model.PassengerJourneyLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerJourneysRepository extends MongoRepository<PassengerJourneyLog,String> {
    List<PassengerJourneyLog> findByRoute(int route);
}
