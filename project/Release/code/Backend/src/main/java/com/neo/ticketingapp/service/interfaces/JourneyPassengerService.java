package com.neo.ticketingapp.service.interfaces;

import com.neo.ticketingapp.model.JourneyPassenger;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;

public interface JourneyPassengerService {
    String insertJourneyPassenger(JourneyPassenger journeyPassenger);
    JourneyPassenger getJourneyByJourneyID(String journeyID);
    String addPassenger(String journeyID, String travelCardID);
    String removePassenger(String journeyID, String travelCardID);
    List<JourneyPassenger> getAllCurrentJourneys();
    JSONObject getAllCurrentJourneysWithDetail();
    void deleteJourneyPassenger(String journeyID) throws IllegalAccessException;
    List<HashMap> getJourneyPassengers(String journeyID) throws IllegalAccessException;
}
