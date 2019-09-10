package com.neo.ticketingapp.service.interfaces;

import com.neo.ticketingapp.model.Route;
import org.json.simple.JSONObject;

import java.util.List;

public interface RouteService {
    String addRoute(Route route);
    Route getRouteByRouteName(String routeName);
    Route getRouteByRouteID(String routeID);
    List<Route> getAllRoutes();
    JSONObject getAllRouteNames();
}