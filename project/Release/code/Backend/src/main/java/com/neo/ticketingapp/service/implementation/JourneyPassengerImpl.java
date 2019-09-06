package com.neo.ticketingapp.service.implementation;

import com.neo.ticketingapp.model.Journey;
import com.neo.ticketingapp.model.JourneyPassenger;
import com.neo.ticketingapp.model.Route;
import com.neo.ticketingapp.repository.JourneyPassengerRepository;
import com.neo.ticketingapp.service.interfaces.JourneyPassengerService;
import com.neo.ticketingapp.service.interfaces.JourneyService;
import com.neo.ticketingapp.service.interfaces.RouteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JourneyPassengerImpl implements JourneyPassengerService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private JourneyPassengerRepository journeyPassengerRepository;

    @Autowired
    private JourneyService journeyService;

    @Autowired
    private RouteService routeService;

    @Override
    public String insertJourneyPassenger(JourneyPassenger journeyPassenger) throws IllegalAccessException {
        if(getJourneyByJourneyID(journeyPassenger.getJourneyID()) == null){
            journeyPassengerRepository.insert(journeyPassenger);
            return "true";
        }
        return "Journey Already Exist !";
    }

    @Override
    public String addPassenger(String journeyID, String travelCardID) throws IllegalAccessException {
        JourneyPassenger journeyPassenger;
        if((journeyPassenger = getJourneyByJourneyID(journeyID)) != null){
            List<String> passengerList = new ArrayList<String>();
            if ((passengerList = journeyPassenger.getTravelCardList()).isEmpty()) {
                passengerList = new ArrayList<String>();
            }
            passengerList.add(travelCardID);
            journeyPassenger.setTravelCardList(passengerList);
            journeyPassengerRepository.save(journeyPassenger);
            return "true";
        }
        return "Journey does not Exist !";
    }

    @Override
    public String removePassenger(String journeyID, String travelCardID) throws IllegalAccessException {
        JourneyPassenger journeyPassenger;
        if((journeyPassenger = getJourneyByJourneyID(journeyID)) != null){
            List<String> passengerList = new ArrayList<String>();
            if ((passengerList = journeyPassenger.getTravelCardList()).isEmpty()) {
                passengerList = new ArrayList<String>();
            }
            int index = 0;
            for( String passengerTemp : passengerList){
                if(passengerTemp.equals(travelCardID)){
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
        if (journeyPassengerList == null || journeyPassengerList.size() == 0) {
            return null;
        }
        return journeyPassengerList.get(0);
    }

    @Override
    public List<JourneyPassenger> getAllCurrentJourneys() {
        return journeyPassengerRepository.findAll();
    }

    @Override
    public JSONObject getAllCurrentJourneysWithDetail(){
        List<JourneyPassenger> journeyPassengerList = getAllCurrentJourneys();
        JSONObject jsonObject = new JSONObject();
        for (JourneyPassenger journeyPassenger: journeyPassengerList) {
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
}
