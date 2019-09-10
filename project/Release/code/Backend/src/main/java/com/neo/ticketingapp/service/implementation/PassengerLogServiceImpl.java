package com.neo.ticketingapp.service.implementation;

import com.neo.ticketingapp.model.PassengerLog;
import com.neo.ticketingapp.repository.PassengerLogRepository;
import com.neo.ticketingapp.service.interfaces.PassengerLogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerLogServiceImpl implements PassengerLogService {
    private static final Logger logger = LogManager.getLogger(PassengerLogServiceImpl.class);

    @Autowired
    PassengerLogRepository passengerLogRepository;

    @Override
    public PassengerLog insertLog(PassengerLog passengerLog) throws IllegalAccessException {
         return passengerLogRepository.save(passengerLog);
    }

    @Override
    public String updateLogDetails(String logID, PassengerLog passengerLog){
        PassengerLog updatedPassengerLog = getLogByLogID(logID);
        if(updatedPassengerLog != null){
            updatedPassengerLog.setEndTime(passengerLog.getEndTime());
            passengerLogRepository.save(updatedPassengerLog);
            return "SUCCESS";
        }
        return "FAILURE";
    }

    @Override
    public PassengerLog getLogByLogID(String logID){
        List<PassengerLog> passengerLogList = passengerLogRepository.findByLogID(logID);
        if(passengerLogList == null || passengerLogList.isEmpty()){
            return null;
        }
        return passengerLogList.get(0);
    }

    @Override
    public List<PassengerLog> getLogsByTravelCardID(String travelCardID) {
        List<PassengerLog> passengerLogList = passengerLogRepository.findByTravelCardID(travelCardID);
        return passengerLogList;
    }
}
