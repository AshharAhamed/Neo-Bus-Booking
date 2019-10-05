package com.neo.ticketingapp.response.model;

public class StringResponse {
    private String string;

    public StringResponse(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
