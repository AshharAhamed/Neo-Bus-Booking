package com.neo.ticketingapp.service.interfaces;

import java.util.List;

import com.neo.ticketingapp.model.PassengerJourneyLog;

public interface IPassengerJourneysLogService {
	List<PassengerJourneyLog> getLogsByRoute(int route);
}
