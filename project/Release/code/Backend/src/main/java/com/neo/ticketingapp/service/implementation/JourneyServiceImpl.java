package com.neo.ticketingapp.service.implementation;

import com.neo.ticketingapp.model.Journey;
import com.neo.ticketingapp.model.Route;
import com.neo.ticketingapp.repository.JourneyRepository;
import com.neo.ticketingapp.service.interfaces.JourneyService;
import com.neo.ticketingapp.service.interfaces.RouteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JourneyServiceImpl implements JourneyService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private RouteService routeService;

    @Autowired
    private JourneyRepository journeyRepository;

    @Override
    public String addJourney(Journey journey) {
        Route route;
        if(( route = routeService.getRouteByRouteID(journey.getRouteID())) != null) {
            journey.setNextStation(route.getBusHalts().get(0));
            journeyRepository.insert(journey);
            return "Journey Added Successfully !";
        }else{
            return "Route does not exist !";
        }
    }

    @Override
    public Journey getJourneyByJourneyID(String journeyID) {
        logger.debug("Request received to get the Route with Journey - {}", journeyID);
        List<Journey> journeyList = journeyRepository.findByJourneyID(journeyID);
        if (journeyList == null || journeyList.size() == 0) {
            return null;
        }
        return journeyList.get(0);
    }

    @Override
    public Journey getJourneyByRouteID(String routeID) {
        logger.debug("Request received to get the Route with Journey - {}", routeID);
        List<Journey> journeyList = journeyRepository.findByRouteID(routeID);
        if (journeyList == null || journeyList.size() == 0) {
            return null;
        }
        return journeyList.get(0);
    }

    @Override
    public Journey getJourneyByBusNo(String busNo) {
        logger.debug("Request received to get the Route with Journey - {}", busNo);
        List<Journey> journeyList = journeyRepository.findByBusNo(busNo);
        if (journeyList == null || journeyList.size() == 0) {
            return null;
        }
        return journeyList.get(0);
    }
}
