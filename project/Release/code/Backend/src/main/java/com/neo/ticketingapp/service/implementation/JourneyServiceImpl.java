package com.neo.ticketingapp.service.implementation;

import com.neo.ticketingapp.model.Journey;
import com.neo.ticketingapp.model.JourneyPassenger;
import com.neo.ticketingapp.model.Route;
import com.neo.ticketingapp.model.TravelCard;
import com.neo.ticketingapp.repository.JourneyRepository;
import com.neo.ticketingapp.service.interfaces.JourneyPassengerService;
import com.neo.ticketingapp.service.interfaces.JourneyService;
import com.neo.ticketingapp.service.interfaces.RouteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class JourneyServiceImpl implements JourneyService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private RouteService routeService;

    @Autowired
    private JourneyPassengerService journeyPassengerService;

    @Autowired
    private JourneyRepository journeyRepository;

    @Override
    public String addJourney(Journey journey, String routeName) throws IllegalAccessException {
        Route route;
        if ((route = routeService.getRouteByRouteName(routeName)) != null) {
            journey.setNextStation(route.getBusHalts().get(0));
            journey.setRouteID(route.getRouteID());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            journey.setStartTime(now);
            Journey newJourney = journeyRepository.insert(journey);
            JourneyPassenger journeyPassenger = new JourneyPassenger();
            journeyPassenger.setJourneyID(newJourney.getJourneyID());
            journeyPassengerService.insertJourneyPassenger(journeyPassenger);
            return "Journey Added Successfully !";
        } else {
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

    @Override
    public List<Journey> getAllJourneys() {
        logger.debug("Request received to get all Journeys");
        return journeyRepository.findAll();
    }

    @Override
    public List<HashMap> getAllActiveJourneys() {
        logger.debug("Request received to get all Journeys");
        List<Journey> journeyList = journeyRepository.findAll();
        List<HashMap> activeJourneyList = new ArrayList<>();
        for (Journey journey : journeyList) {
            if (!journey.getNextStation().equals("END")) {
                HashMap<String, String> tempMap = new HashMap<>();
                tempMap.put("journeyID", journey.getJourneyID());
                tempMap.put("routeID", journey.getRouteID());
                tempMap.put("busNo", journey.getBusNo());
                tempMap.put("startTime", journey.getStartTime().toString());
                tempMap.put("nextStation", journey.getNextStation());
                tempMap.put("routeName", routeService.getRouteByRouteID(journey.getRouteID()).getRouteName());
                activeJourneyList.add(tempMap);
            }
        }
        return activeJourneyList;
    }

    @Override
    public String deleteJourney(String journeyID) throws IllegalAccessException {
        logger.debug("Request received to delete the {}", journeyID);
        Journey journey = getJourneyByJourneyID(journeyID);
        journeyRepository.delete(journey);
        journeyPassengerService.deleteJourneyPassenger(journeyID);
        logger.info("{} is successfully deleted", journeyID);
        return "Journey " + journeyID + "removed Successfully !";
    }

}
