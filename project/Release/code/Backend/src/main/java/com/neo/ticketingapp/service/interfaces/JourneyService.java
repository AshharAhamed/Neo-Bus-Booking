package com.neo.ticketingapp.service.interfaces;

import com.neo.ticketingapp.model.Journey;

import java.util.HashMap;
import java.util.List;

public interface JourneyService {
    String addJourney(Journey journey, String routeName) throws IllegalAccessException;
    Journey getJourneyByJourneyID(String journeyID);
    Journey getJourneyByRouteID(String routeID);
    Journey getJourneyByBusNo(String busNo);
    List<Journey> getAllJourneys();
    List<HashMap> getAllActiveJourneys();
    public String deleteJourney(String journeyID) throws IllegalAccessException;
}
