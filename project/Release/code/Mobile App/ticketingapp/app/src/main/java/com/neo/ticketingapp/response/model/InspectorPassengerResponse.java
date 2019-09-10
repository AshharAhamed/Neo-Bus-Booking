package com.neo.ticketingapp.response.model;

public class InspectorPassengerResponse {
    private String name;
    private String nic;
    private String travelCardID;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getTravelCardID() {
        return travelCardID;
    }

    public void setTravelCardID(String travelCardID) {
        this.travelCardID = travelCardID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
