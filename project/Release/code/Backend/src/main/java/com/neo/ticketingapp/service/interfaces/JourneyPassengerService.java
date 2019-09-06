package com.neo.ticketingapp.service.interfaces;

import com.neo.ticketingapp.model.JourneyPassenger;
import com.neo.ticketingapp.model.Route;
import org.json.simple.JSONObject;

import java.util.List;

public interface JourneyPassengerService {
    String insertJourneyPassenger(JourneyPassenger journeyPassenger) throws IllegalAccessException;
    JourneyPassenger getJourneyByJourneyID(String journeyID);
    String addPassenger(String journeyID, String travelCardID) throws IllegalAccessException;
    String removePassenger(String journeyID, String travelCardID) throws IllegalAccessException;
    List<JourneyPassenger> getAllCurrentJourneys();
    public JSONObject getAllCurrentJourneysWithDetail();
    public void deleteJourneyPassenger(String journeyID) throws IllegalAccessException;
}
