package com.neo.ticketingapp.controller;

import com.neo.ticketingapp.model.Journey;
import com.neo.ticketingapp.service.interfaces.JourneyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/journey")
public class JourneyController {

    private static final Logger logger = LogManager.getLogger(JourneyController.class);

    @Autowired
    private JourneyService journeyService;

    @PostMapping(value = "/add/{routeName}")
    public ResponseEntity<String> addJourney(@RequestBody Journey journey, @PathVariable String routeName) {
        logger.debug("Request received to add a journey to the system");
        try {
            if (journey != null) {
                return new ResponseEntity<>(journeyService.addJourney(journey, routeName), HttpStatus.CREATED);
            }
        } catch (IllegalArgumentException | IllegalAccessException | ParseException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("Journey Object is Empty", HttpStatus.NO_CONTENT);
    }


    @GetMapping(value = "/getAllActiveJourneys")
    public List<HashMap> getJourneys() {
        logger.debug("Request received to get all Route Names");
        return journeyService.getAllActiveJourneys();
    }

    @DeleteMapping(value = "/delete/{journeyID}")
    public ResponseEntity<String> deleteUser(@PathVariable String journeyID) {
        logger.debug("Request received to delete a journey");
        try {
            if (journeyID != null) {
                return new ResponseEntity<>(journeyService.deleteJourney(journeyID), HttpStatus.OK);
            }
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (IllegalAccessException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Username is empty.", HttpStatus.NO_CONTENT);
    }

}
