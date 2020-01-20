package com.neo.ticketingapp.service.implementation;

import com.neo.ticketingapp.model.Journey;
import com.neo.ticketingapp.model.JourneyPassenger;
import com.neo.ticketingapp.model.Passenger;
import com.neo.ticketingapp.model.RoguePassenger;
import com.neo.ticketingapp.model.Route;
import com.neo.ticketingapp.repository.JourneyPassengerRepository;
import com.neo.ticketingapp.repository.RoguePassengerRepository;
import com.neo.ticketingapp.service.interfaces.JourneyPassengerService;
import com.neo.ticketingapp.service.interfaces.JourneyService;
import com.neo.ticketingapp.service.interfaces.PassengerService;
import com.neo.ticketingapp.service.interfaces.RouteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class JourneyPassengerServiceImpl implements JourneyPassengerService {

    private static final Logger logger = LogManager.getLogger(JourneyPassengerServiceImpl.class);

    @Autowired
    private JourneyPassengerRepository journeyPassengerRepository;

    @Autowired
    private RoguePassengerRepository roguePassengerRepository;

    @Autowired
    private JourneyService journeyService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private PassengerService passengerService;

    @Override
    public String insertJourneyPassenger(JourneyPassenger journeyPassenger) {
        if (getJourneyByJourneyID(journeyPassenger.getJourneyID()) == null) {
            journeyPassengerRepository.insert(journeyPassenger);
            return "true";
        }
        return "Journey Already Exist !";
    }

    @Override
    public String addPassenger(String journeyID, String travelCardID) {
        JourneyPassenger journeyPassenger;
        if ((journeyPassenger = getJourneyByJourneyID(journeyID)) != null) {
            List<String> passengerList = journeyPassenger.getTravelCardList();
            passengerList.add(travelCardID);
            journeyPassenger.setTravelCardList(passengerList);
            journeyPassengerRepository.save(journeyPassenger);
            return "true";
        }
        return "Journey does not Exist !";
    }

    @Override
    public String removePassenger(String journeyID, String travelCardID) {
        JourneyPassenger journeyPassenger;
        if ((journeyPassenger = getJourneyByJourneyID(journeyID)) != null) {
            List<String> passengerList = journeyPassenger.getTravelCardList();
            int index = 0;
            for (String passengerTemp : passengerList) {
                if (passengerTemp.equals(travelCardID)) {
                    passengerList.remove(index);
                    break;
                }
                ++index;
            }
            journeyPassenger.setTravelCardList(passengerList);
            journeyPassengerRepository.save(journeyPassenger);
            return "true";
        }
        return "Journey does not Exist !";
    }

    @Override
    public JourneyPassenger getJourneyByJourneyID(String journeyID) {
        logger.debug("Request received to get the Route with Name - {}", journeyID);
        List<JourneyPassenger> journeyPassengerList = journeyPassengerRepository.findByJourneyID(journeyID);
        if (journeyPassengerList == null || journeyPassengerList.isEmpty()) {
            return null;
        }
        return journeyPassengerList.get(0);
    }

    @Override
    public List<JourneyPassenger> getAllCurrentJourneys() {
        return journeyPassengerRepository.findAll();
    }

    @Override
    public JSONObject getAllCurrentJourneysWithDetail() {
        List<JourneyPassenger> journeyPassengerList = getAllCurrentJourneys();
        JSONObject jsonObject = new JSONObject();
        for (JourneyPassenger journeyPassenger : journeyPassengerList) {
            Journey journey = journeyService.getJourneyByJourneyID(journeyPassenger.getJourneyID());
            Route route = routeService.getRouteByRouteID(journey.getRouteID());

            jsonObject.put("journeyID", journeyPassenger.getJourneyID());
            jsonObject.put("busNo", journey.getBusNo());
            jsonObject.put("routeName", route.getRouteName());
            jsonObject.put("passengers", journeyPassenger.getTravelCardList());
        }
        return jsonObject;
    }

    @Override
    public void deleteJourneyPassenger(String journeyID) throws IllegalAccessException {
        logger.debug("Request received to delete the {}", journeyID);
        JourneyPassenger journeyPassenger = getJourneyByJourneyID(journeyID);
        journeyPassengerRepository.delete(journeyPassenger);
        logger.info("{} is successfully deleted", journeyID);
    }

    @Override
    public List<HashMap> getJourneyPassengers(String journeyID) throws IllegalAccessException {
        JourneyPassenger journeyPassenger = getJourneyByJourneyID(journeyID);
        List<HashMap> activePassengerList = new ArrayList<>();
        for (String travelCardID : journeyPassenger.getTravelCardList()) {
            Passenger passenger = passengerService.getPassengerByCardNo(travelCardID);
            HashMap<String, String> tempMap = new HashMap<>();

            tempMap.put("travelCardID", travelCardID);
            tempMap.put("name", passenger.getFirstName() + " " + passenger.getLastName());
            tempMap.put("type", passenger.getType().toString());
            if (passenger.getNic() != null)
                tempMap.put("nic", passenger.getNic());
            else if (passenger.getPassport() != null)
                tempMap.put("nic", passenger.getPassport());

            activePassengerList.add(tempMap);
        }
        return activePassengerList;
    }

    @Override
    public String insertRoguePassenger(RoguePassenger rogue) {
        roguePassengerRepository.insert(rogue);
        return "true";

    }

    @Override
    public List<RoguePassenger> getAllCurrentRogueJourneys() {
        return roguePassengerRepository.findAll();
    }
}
