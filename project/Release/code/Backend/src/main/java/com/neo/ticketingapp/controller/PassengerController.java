package com.neo.ticketingapp.controller;

import com.neo.ticketingapp.model.Passenger;
import com.neo.ticketingapp.service.interfaces.PassengerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/passenger")
public class PassengerController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private PassengerService passengerService;

    @PostMapping(value = "/add")
    public ResponseEntity<String> userRegistration(@RequestBody Passenger passenger) {
        logger.debug("Request received to add a passenger to the system");
        try {
            if (passenger != null) {
                return new ResponseEntity<>(passengerService.insertPassenger(passenger), HttpStatus.CREATED);
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("User Object is Empty", HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<String> updatePassengerDetails(@RequestBody Passenger passenger) {
        logger.debug("Request received to passenger the user details");
        try {
            if (passenger != null) {
                return new ResponseEntity<>(passengerService.updatePassengerDetails(passenger), HttpStatus.OK);
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("User Object is Empty", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/delete/{cardID}")
    public ResponseEntity<String> deletePassenger(@PathVariable String cardID) {
        logger.debug("Request received to delete a passenger");

        try {
            if (cardID != null) {
                return new ResponseEntity<>(passengerService.deletePassenger(cardID), HttpStatus.OK);
            }
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (IllegalAccessException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Card ID is empty.", HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/getAllPassengers/{type}")
    public List<Passenger> getPassengers(@PathVariable String type) {
        logger.debug("Request received to get all Service users");
        return passengerService.getAllPassengers(type);
    }
}
