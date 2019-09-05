package com.neo.ticketingapp.service.implementation;

import com.neo.ticketingapp.enums.PassengerType;
import com.neo.ticketingapp.model.Card;
import com.neo.ticketingapp.model.Passenger;
import com.neo.ticketingapp.model.Route;
import com.neo.ticketingapp.repository.RouteRepository;
import com.neo.ticketingapp.service.interfaces.RouteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public String addRoute(Route route) {
        if ((getRouteByRouteName(route.getRouteName())) == null) {
            List<String> busHalts = route.getBusHalts();
            busHalts.add("END");
            routeRepository.insert(route);
            return "Route Added Successfully !";
        } else
            return "Route already Exist !";
    }

    @Override
    public Route getRouteByRouteName(String routeName) {
        logger.debug("Request received to get the Route with Name - {}", routeName);
        List<Route> routeList = routeRepository.findByRouteName(routeName);
        if (routeList == null || routeList.size() == 0) {
            return null;
        }
        return routeList.get(0);
    }

    @Override
    public Route getRouteByRouteID(String routeID) {
        logger.debug("Request received to get the Route with Name - {}", routeID);
        List<Route> routeList = routeRepository.findByRouteID(routeID);
        if (routeList == null || routeList.size() == 0) {
            return null;
        }
        return routeList.get(0);
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }
}