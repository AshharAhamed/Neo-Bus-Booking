package com.neo.ticketingapp.response.model;

import java.io.Serializable;

public class Card implements Serializable {
    private String cardId;
    private String cardNo;
    private String expiryDate;
    private String ccNo;

    public Card() {
    }

    public Card(String cardNo, String expiryDate, String ccNo) {
        this.cardNo = cardNo;
        this.expiryDate = expiryDate;
        this.ccNo = ccNo;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCcNo() {
        return ccNo;
    }

    public void setCcNo(String ccNo) {
        this.ccNo = ccNo;
    }
}
