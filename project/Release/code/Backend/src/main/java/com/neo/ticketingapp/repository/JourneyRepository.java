package com.neo.ticketingapp.repository;

import com.neo.ticketingapp.model.Journey;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JourneyRepository  extends MongoRepository<Journey,String> {
    List<Journey> findByJourneyID(String journeyID);
    List<Journey> findByRouteID(String routeID);
    List<Journey> findByBusNo(String busNo);
}
