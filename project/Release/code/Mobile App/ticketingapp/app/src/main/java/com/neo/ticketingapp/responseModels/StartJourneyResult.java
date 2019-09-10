package com.neo.ticketingapp.responseModels;

public class StartJourneyResult {

    private String Message;
    private String Error;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }
}
