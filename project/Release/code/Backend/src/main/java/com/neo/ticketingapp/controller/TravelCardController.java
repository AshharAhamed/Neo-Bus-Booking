package com.neo.ticketingapp.controller;

import com.neo.ticketingapp.model.TravelCard;
import com.neo.ticketingapp.service.interfaces.TravelCardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/travelCard")
public class TravelCardController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private TravelCardService travelCardService;

    @PostMapping(value = "/add")
    public ResponseEntity<String> addCard(@RequestBody TravelCard travelCard) {
        logger.debug("Request received to add a Travel Card to the system");
        try {
            if (travelCard != null) {
                return new ResponseEntity<>(travelCardService.insertTravelCard(travelCard), HttpStatus.CREATED);
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("Travel Card Object is Empty", HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<String> updateTravelCardDetails(@RequestBody TravelCard travelCard) {
        logger.debug("Request received to update the Travel Card details");
        try {
            if (travelCard != null) {
                return new ResponseEntity<>(travelCardService.updateTravelCardActiveStatus(travelCard), HttpStatus.OK);
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("Travel Card Object is Empty", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/delete/{cardNo}")
    public ResponseEntity<String> deleteUser(@PathVariable String cardNo) {
        logger.debug("Request received to delete a Travel Card");

        try {
            if (cardNo != null) {
                travelCardService.deleteTravelCard(cardNo);
                return new ResponseEntity<>("Travel Card is Deleted.", HttpStatus.OK);
            }
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (IllegalAccessException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Travel Card is empty.", HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/getAllTravelCards/{status}")
    public List<TravelCard> getServiceUsers(@PathVariable String status) {
        logger.debug("Request received to get all Travel Cards");
        return travelCardService.getAllTravelCards(status);
    }
}
