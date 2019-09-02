package com.neo.ticketingapp.repository;

import com.neo.ticketingapp.model.TravelCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelCardRepository extends MongoRepository<TravelCard,String> {
    List<TravelCard> findByCardNo(String cardNo);
    List<TravelCard> findByAccountId(String accountId);
    List<TravelCard> findByActive(boolean active);
}
