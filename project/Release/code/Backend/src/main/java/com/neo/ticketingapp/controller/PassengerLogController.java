package com.neo.ticketingapp.controller;

import com.neo.ticketingapp.model.PassengerLog;
import com.neo.ticketingapp.service.interfaces.PassengerLogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "https://neo-bus-frontend.herokuapp.com"})
@RestController
@RequestMapping("/log")
public class PassengerLogController {
    private static final Logger logger = LogManager.getLogger(PassengerLogController.class);
    @Autowired
    PassengerLogService passengerLogService;

    @GetMapping(value = "/get/{travelCardID}")
    public List<PassengerLog> getLogsByTravelCardID(@PathVariable String travelCardID) {
        logger.debug("Request received to get all Service users");
        return passengerLogService.getLogsByTravelCardID(travelCardID);
    }
}
