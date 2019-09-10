package com.neo.ticketingapp.service.interfaces;

import com.neo.ticketingapp.model.Card;

public interface CardService {
    Card getCardByCardNo(String cardNo);
    String insertCard(Card card) throws IllegalAccessException;
    String deleteCard(String cardNo);
}
