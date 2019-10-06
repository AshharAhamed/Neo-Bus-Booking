package com.neo.ticketingapp.controller;

import com.neo.ticketingapp.model.Card;
import com.neo.ticketingapp.service.interfaces.CardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000", "https://neo-bus-frontend.herokuapp.com"})
@RestController
@RequestMapping("/card")
public class CardController {

    private static final Logger logger = LogManager.getLogger(CardController.class);

    @Autowired
    private CardService cardService;

    @PostMapping(value = "/add")
    public ResponseEntity<String> userRegistration(@RequestBody Card card) {
        logger.debug("Request received to add a passenger to the system");
        try {
            if (card != null) {
                return new ResponseEntity<>(cardService.insertCard(card), HttpStatus.CREATED);
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("User Object is Empty", HttpStatus.NO_CONTENT);
    }
}
