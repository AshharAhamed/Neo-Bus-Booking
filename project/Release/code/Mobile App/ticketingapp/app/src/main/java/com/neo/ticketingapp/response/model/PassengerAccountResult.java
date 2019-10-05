package com.neo.ticketingapp.response.model;

import java.util.List;

public class PassengerAccountResult {

    private String accountId;
    private String firstName;
    private String lastName;
    private String email;
    private String contact;
    private String dob;
    private String type;
    private String nic;
    private String passport;
    private String cardNo;
    private String creditBalance;
    private String loanAmount;
    private List<Card> cardList;
    private String loan;

    public PassengerAccountResult() {
    }

    public PassengerAccountResult(String accountId, String firstName, String lastName, String email, String contact, String type, String nic, String cardNo) {
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contact = contact;
        //this.dob = dob;
        this.type = type;
        this.nic = nic;
        this.cardNo = cardNo;
    }


    public PassengerAccountResult(String accountId, String firstName, String lastName, String email, String contact, String dob, String type, String nic, String passport, String cardNo, String creditBalance, String loanAmount, List<Card> cardList, String loan) {
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contact = contact;
        this.dob = dob;
        this.type = type;
        this.nic = nic;
        this.passport = passport;
        this.cardNo = cardNo;
        this.creditBalance = creditBalance;
        this.loanAmount = loanAmount;
        this.cardList = cardList;
        this.loan = loan;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(String creditBalance) {
        this.creditBalance = creditBalance;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public String getLoan() {
        return loan;
    }

    public void setLoan(String loan) {
        this.loan = loan;
    }
}
