package com.neo.ticketingapp.controller;

import com.neo.ticketingapp.model.RoguePassenger;
import com.neo.ticketingapp.service.interfaces.JourneyPassengerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "https://neo-bus-frontend.herokuapp.com"})
@RestController
@RequestMapping("/journeyPassenger")
public class JourneyPassengerController {

    private static final Logger logger = LogManager.getLogger(JourneyPassengerController.class);

    @Autowired
    private JourneyPassengerService journeyPassengerService;

    @GetMapping(value = "/getAll")
    public JSONObject getJourneyWithDetails() {
        logger.debug("Request received to get all Service users");
        return journeyPassengerService.getAllCurrentJourneysWithDetail();
    }

    @GetMapping(value = "/getAllPassengers/{journeyID}")
    public List<HashMap> getJourneyPassengers(@PathVariable String journeyID) throws IllegalAccessException {
        logger.debug("Request received to get all Service users");
        return journeyPassengerService.getJourneyPassengers(journeyID);
    }
    
    @CrossOrigin(origins = "*")
     @PostMapping(value = "/addRooguePassenger")
    public ResponseEntity<String> addRoguePassenger(@RequestBody RoguePassenger roguePassenger) {
        logger.debug("Request received to add a Rogue Passenger to the system");
        try {
            if (roguePassenger != null) {
                return new ResponseEntity<>(journeyPassengerService.insertRoguePassenger(roguePassenger), HttpStatus.CREATED);
            }
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("User Object is Empty", HttpStatus.NO_CONTENT);
    }
     
     @GetMapping(value = "/getAllRoguePassengers")
     public List getJourneyRoguePassengers() throws IllegalAccessException {
         logger.debug("Request received to get all Service users");
         return journeyPassengerService.getAllCurrentRogueJourneys();
     }
     
}
