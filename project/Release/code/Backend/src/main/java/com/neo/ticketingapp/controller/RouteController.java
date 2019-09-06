package com.neo.ticketingapp.controller;

import com.neo.ticketingapp.model.Route;
import com.neo.ticketingapp.service.interfaces.RouteService;
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
@RequestMapping("/route")
public class RouteController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private RouteService routeService;

    @PostMapping(value = "/add")
    public ResponseEntity<String> addRoute(@RequestBody Route route) {
        logger.debug("Request received to add a route to the system");
        try {
            if (route != null) {
                return new ResponseEntity<>(routeService.addRoute(route), HttpStatus.CREATED);
            }
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("User Object is Empty", HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/getAllRoutes")
    public List<Route> getRoutes() {
        logger.debug("Request received to get all Routes");
        return routeService.getAllRoutes();
    }

    @GetMapping(value = "/getAllRouteNames")
    public JSONObject getRouteNames() {
        logger.debug("Request received to get all Route Names");
        return routeService.getAllRouteNames();
    }
}
