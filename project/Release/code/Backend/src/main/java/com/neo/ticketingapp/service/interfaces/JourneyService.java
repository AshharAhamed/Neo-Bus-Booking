package com.neo.ticketingapp.service.interfaces;

import com.neo.ticketingapp.model.Journey;

public interface JourneyService {
    String addJourney(Journey journey);
    Journey getJourneyByJourneyID(String journeyID);
    Journey getJourneyByRouteID(String routeID);
    Journey getJourneyByBusNo(String busNo);
}
