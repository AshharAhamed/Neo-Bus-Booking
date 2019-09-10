package com.neo.ticketingapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class JourneyPassenger {
    @Id
    private String journeyID;
    private List<String> travelCardList;

    public JourneyPassenger() {
        this.travelCardList = new ArrayList<>();
    }

    public String getJourneyID() {
        return journeyID;
    }

    public void setJourneyID(String journeyID) {
        this.journeyID = journeyID;
    }

    public List<String> getTravelCardList() {
        return travelCardList;
    }

    public void setTravelCardList(List<String> travelCardList) {
        this.travelCardList = travelCardList;
    }
}
