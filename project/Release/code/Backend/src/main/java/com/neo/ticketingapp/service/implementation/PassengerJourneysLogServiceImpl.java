package com.neo.ticketingapp.service.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.ticketingapp.model.PassengerJourneyLog;
import com.neo.ticketingapp.repository.PassengerJourneysRepository;
import com.neo.ticketingapp.service.interfaces.IPassengerJourneysLogService;

@Service
public class PassengerJourneysLogServiceImpl implements IPassengerJourneysLogService {

    @Autowired
    PassengerJourneysRepository passengerJourneysRepository;

    @Override
    public List<PassengerJourneyLog> getLogsByRoute(int route) {
        return null;
    }

}
