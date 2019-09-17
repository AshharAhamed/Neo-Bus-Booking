package com.neo.ticketingapp.service.interfaces;

import com.neo.ticketingapp.model.PassengerLog;

import java.util.List;

public interface PassengerLogService {
    PassengerLog insertLog(PassengerLog passengerLog) throws IllegalAccessException;
    String updateLogDetails(String logID, PassengerLog passengerLog);
    PassengerLog getLogByLogID(String logID);
    List<PassengerLog> getLogsByTravelCardID(String travelCardID);
}
