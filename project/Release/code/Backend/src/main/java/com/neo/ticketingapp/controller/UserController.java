package com.neo.ticketingapp.controller;

import com.neo.ticketingapp.entity.GeneralUser;
import com.neo.ticketingapp.model.Passenger;
import com.neo.ticketingapp.model.User;
import com.neo.ticketingapp.service.interfaces.PassengerService;
import com.neo.ticketingapp.service.interfaces.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private PassengerService passengerService;

    @PostMapping(value = "/add")
    public ResponseEntity<String> userRegistration(@RequestBody User user) {
        logger.debug("Request received to add a user to the system");
        try {
            if (user != null) {
                return new ResponseEntity<>(userService.insertUser(user), HttpStatus.CREATED);
            }
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("User Object is Empty", HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<String> updateUserDetails(@RequestBody User user) {
        logger.debug("Request received to update the user details");
        try {
            if (user != null) {
                return new ResponseEntity<>(userService.updateUserDetails(user), HttpStatus.OK);
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("User Object is Empty", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/delete/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        logger.debug("Request received to delete a user");

        try {
            if (username != null) {
                userService.deleteUser(username);
                return new ResponseEntity<>("User is Deleted.", HttpStatus.OK);
            }
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (IllegalAccessException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Username is empty.", HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/log")
    public ResponseEntity<GeneralUser> logUser(@RequestBody GeneralUser generalUser) {
        logger.debug("Request received from user to logging to the system");
        try {
            if (generalUser != null) {
                User user;
                if ((user = userService.logUser(generalUser.getUsername(), generalUser.getPassword())) != null){
                    generalUser.setPassword("");
                    generalUser.setType(user.getType().toString());
                    return new ResponseEntity<>(generalUser, HttpStatus.OK);
                }else{
                    Passenger passenger;
                    if((passenger = passengerService.logPassenger(generalUser.getUsername(), generalUser.getPassword())) != null){
                        generalUser.setPassword("");
                        generalUser.setType(passenger.getType().toString());
                        return new ResponseEntity<>(generalUser, HttpStatus.OK);
                    }else{
                        return new ResponseEntity<>(null, HttpStatus.OK);
                    }
                }
            }
        } catch (IllegalArgumentException ex) {
            logger.info(ex.getMessage());
            return new ResponseEntity<>( null, HttpStatus.NOT_ACCEPTABLE);
        } catch (IllegalAccessException ex) {
            logger.info(ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/getAllUsers/{type}")
    public List<User> getServiceUsers(@PathVariable String type) {
        logger.debug("Request received to get all Service users");
        return userService.getAllUsers(type);
    }
}
