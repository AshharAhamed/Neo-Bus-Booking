package com.neo.ticketingapp.controller;

import com.neo.ticketingapp.service.interfaces.JourneyPassengerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
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
}
