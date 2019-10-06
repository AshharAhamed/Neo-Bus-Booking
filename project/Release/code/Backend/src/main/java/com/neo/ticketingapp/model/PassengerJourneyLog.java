package com.neo.ticketingapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class PassengerJourneyLog  {

    @Id
    private String logID;
    private String travelCardID;
    private int route; 
	private String journeyID;
    private Double ticketPrice;
    private Date startTime;
    private Date endTime;
    private String startStation;
    private String endStation;
    


    public String getLogID() {
        return logID;
    }

    public void setLogID(String logID) {
        this.logID = logID;
    }

    public String getTravelCardID() {
        return travelCardID;
    }

    public void setTravelCardID(String travelCardID) {
        this.travelCardID = travelCardID;
    }
    
    public int getRoot() {
		return route;
	}

	public void setRoot(int root) {
		this.route = root;
	}

    public String getJourneyID() {
        return journeyID;
    }

    public void setJourneyID(String journeyID) {
        this.journeyID = journeyID;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
}
