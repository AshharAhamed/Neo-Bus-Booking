/**
 *
 */
package com.neo.ticketingapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Ashhar
 *
 */

@Document
public class RoguePassenger {
    @Id
    private String rougePassengerId;
    private String name;
    private String contact;
    private String nic;
    private String passport;
    private double loanAmount;


    public RoguePassenger() {
    }

    public String getRougePassengerId() {
        return rougePassengerId;
    }

    public void setRougePassengerId(String rougePassengerId) {
        this.rougePassengerId = rougePassengerId;
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


}
