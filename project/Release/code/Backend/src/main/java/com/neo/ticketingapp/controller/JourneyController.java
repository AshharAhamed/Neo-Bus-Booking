package com.neo.ticketingapp.controller;

import com.neo.ticketingapp.model.Journey;
import com.neo.ticketingapp.model.Route;
import com.neo.ticketingapp.service.interfaces.JourneyService;
import com.neo.ticketingapp.service.interfaces.RouteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/journey")
public class JourneyController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private JourneyService journeyService;

    @PostMapping(value = "/add")
    public ResponseEntity<String> addJourney(@RequestBody Journey journey) {
        logger.debug("Request received to add a journey to the system");
        try {
            if (journey != null) {
                return new ResponseEntity<>(journeyService.addJourney(journey), HttpStatus.CREATED);
            }
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("Journey Object is Empty", HttpStatus.NO_CONTENT);
    }


}
