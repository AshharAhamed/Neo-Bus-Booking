package com.neo.ticketingapp.response.model;

public class RoguePassengerResponse {
    private String rougePassenngerId;
    private String name;
    private String contact;
    private String nic;
    private String passport;
    private double loanAmount;
    private String routNumber;

    public RoguePassengerResponse() {

    }

    public RoguePassengerResponse(String name, String contact, String nic, String passport, double loanAmount, String routNumber) {
        this.name = name;
        this.contact = contact;
        this.nic = nic;
        this.passport = passport;
        this.loanAmount = loanAmount;
        this.routNumber = routNumber;
    }

    public String getRougePassenngerId() {
        return rougePassenngerId;
    }

    public void setRougePassenngerId(String rougePassenngerId) {
        this.rougePassenngerId = rougePassenngerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getRoutNumber() {
        return routNumber;
    }

    public void setRoutNumber(String routNumber) {
        this.routNumber = routNumber;
    }
}
