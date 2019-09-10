package com.neo.ticketingapp.repository;

import com.neo.ticketingapp.model.PassengerLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerLogRepository extends MongoRepository<PassengerLog,String> {
    List<PassengerLog> findByTravelCardID(String travelCardID);
    List<PassengerLog> findByLogID(String logID);
}
