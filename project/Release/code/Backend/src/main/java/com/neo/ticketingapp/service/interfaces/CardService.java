package com.neo.ticketingapp.service.interfaces;

import com.neo.ticketingapp.model.Card;

public interface CardService {
    public Card getCardByCardNo(String cardNo);
    String insertCard(Card card) throws IllegalAccessException;
    public String deleteCard(String cardNo);
}
