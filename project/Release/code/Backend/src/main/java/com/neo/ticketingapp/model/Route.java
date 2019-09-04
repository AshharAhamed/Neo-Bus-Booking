package com.neo.ticketingapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Route {

    @Id
    private String routeID;
    private String routeName;
    private String routeNo;

    public String getRouteNo() {
        return routeNo;
    }

    public void setRouteNo(String routeNo) {
        this.routeNo = routeNo;
    }

    private List<String> busHalts;

    public String getRouteID() {
        return routeID;
    }

    public void setRouteID(String routeID) {
        this.routeID = routeID;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public List<String> getBusHalts() {
        return busHalts;
    }

    public void setBusHalts(List<String> busHalts) {
        this.busHalts = busHalts;
    }
}
