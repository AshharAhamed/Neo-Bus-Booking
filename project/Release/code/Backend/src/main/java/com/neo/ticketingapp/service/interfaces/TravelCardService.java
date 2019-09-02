package com.neo.ticketingapp.service.interfaces;

import com.neo.ticketingapp.model.TravelCard;

import java.util.List;

public interface TravelCardService {
    TravelCard getTravelCardByNo(String cardNo) throws IllegalAccessException;

    String insertTravelCard(TravelCard travelCard) throws IllegalAccessException;

    String updateTravelCardActiveStatus(TravelCard travelCard)  throws IllegalAccessException;

    void deleteTravelCard(String cardNo) throws IllegalAccessException;

    List<TravelCard> getAllTravelCards(String activeStatus);
}
