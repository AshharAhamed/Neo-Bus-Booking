package com.neo.ticketingapp.request.model;

public class StartJourneyRequest {
    private String travelCardID;
    private String startStation;
    private String endStation;
    private String journeyID;

    public StartJourneyRequest(String travelCardID, String startStation, String endStation, String journeyID) {
        this.travelCardID = travelCardID;
        this.startStation = startStation;
        this.endStation = endStation;
        this.journeyID = journeyID;
    }

    public String getTravelCardID() {
        return travelCardID;
    }

    public void setTravelCardID(String travelCardID) {
        this.travelCardID = travelCardID;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getJourneyID() {
        return journeyID;
    }

    public void setJourneyID(String journeyID) {
        this.journeyID = journeyID;
    }
}
