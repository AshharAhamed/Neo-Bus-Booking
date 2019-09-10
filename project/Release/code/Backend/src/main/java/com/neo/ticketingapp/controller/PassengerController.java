package com.neo.ticketingapp.controller;

import com.neo.ticketingapp.model.Card;
import com.neo.ticketingapp.model.Passenger;
import com.neo.ticketingapp.service.interfaces.PassengerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/passenger")
public class PassengerController {
    private static final Logger logger = LogManager.getLogger(PassengerController.class);
    private static final String EMPTY_USER_OBJECT = "User Object is Empty";

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
        return new ResponseEntity<>(EMPTY_USER_OBJECT, HttpStatus.NO_CONTENT);
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
        return new ResponseEntity<>(EMPTY_USER_OBJECT, HttpStatus.NO_CONTENT);
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

    @GetMapping(value = "/getPassenger/{cardNo}")
    public ResponseEntity<Passenger> getPassenger(@PathVariable String cardNo) {
        logger.debug("Request received to get Passenger");
        return new ResponseEntity<>(passengerService.getPassenger(cardNo), HttpStatus.OK);
    }

    @GetMapping(value = "/getPassengerAccount/{cardNo}")
    public ResponseEntity<Passenger> getPassengerAccount(@PathVariable String cardNo) {
        logger.debug("Request received to get Passenger Account");
        return new ResponseEntity<>(passengerService.getPassenger(cardNo), HttpStatus.OK);
    }

    @PostMapping(value = "/addCard/{cardNo}")
    public ResponseEntity<String> addPaymentCard(@RequestBody Card card, @PathVariable String cardNo) {
        logger.debug("Request received to add a card to passenger to the system");
        try {
            if (card != null) {
                return new ResponseEntity<>(passengerService.addCard(cardNo, card), HttpStatus.CREATED);
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(EMPTY_USER_OBJECT, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/deleteCard/{travelCardNo}/{cardNo}")
    public ResponseEntity<String> deletePaymentCard(@PathVariable String travelCardNo, @PathVariable String cardNo) {
        logger.debug("Request received to add a card to passenger to the system");
        try {
            return new ResponseEntity<>(passengerService.deleteCard(travelCardNo, cardNo), HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/getCard/{travelCardNo}")
    public ResponseEntity<List> getCards(@PathVariable String travelCardNo) {
        logger.debug("Request received to get Passenger Payments Cards");
        return new ResponseEntity<>(passengerService.getCards(travelCardNo), HttpStatus.OK);
    }

    @PostMapping(value = "/topUp/{travelCardNo}")
    public ResponseEntity<String> topUp(@PathVariable String travelCardNo, @RequestBody JSONObject sampleObject) {
        logger.debug("Request received to top up a passenger in the system");
        try {
            if (travelCardNo != null) {
                double amount = Double.parseDouble(sampleObject.get("amount").toString()) ;
                return new ResponseEntity<>(passengerService.topUp(travelCardNo, sampleObject.get("paymentCardNo").toString(), amount), HttpStatus.CREATED);
            }
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(EMPTY_USER_OBJECT, HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/startJourney")
    public ResponseEntity<JSONObject> startJourney(@RequestBody JSONObject jsonObject) {
        logger.debug("Request received to start to the system");
        try {
            if (jsonObject != null) {
                return new ResponseEntity<>(passengerService.startJourney(jsonObject.get("travelCardID").toString(), jsonObject.get("startStation").toString(), jsonObject.get("endStation").toString(), jsonObject.get("journeyID").toString()), HttpStatus.CREATED);
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            return new ResponseEntity<>((JSONObject) new JSONObject().put("Error", ex.getMessage()), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>((JSONObject) new JSONObject().put("Error", "Empty JSON Object"), HttpStatus.NO_CONTENT);
    }
}
