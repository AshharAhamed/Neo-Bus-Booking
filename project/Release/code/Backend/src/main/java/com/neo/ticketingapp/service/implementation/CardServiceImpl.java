package com.neo.ticketingapp.service.implementation;

import com.neo.ticketingapp.model.Card;
import com.neo.ticketingapp.repository.CardRepository;
import com.neo.ticketingapp.service.interfaces.CardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private static final Logger logger = LogManager.getLogger(CardServiceImpl.class);

    @Autowired
    private CardRepository cardRepository;

    @Override
    public String insertCard(Card card) {
        if ((getCardByCardNo(card.getCardNo())) == null) {
            cardRepository.insert(card);
            return "Card Added Successfully !";
        } else
            return "Card Already Exist";
    }

    @Override
    public String deleteCard(String cardNo) {
        cardRepository.delete(getCardByCardNo(cardNo));
        return "Card Sucessfully Deleted !";
    }

    @Override
    public Card getCardByCardNo(String cardNo) {
        logger.debug("Request received to get the Passenger with Card Id - {}", cardNo);
        List<Card> cardList = cardRepository.findByCardNo(cardNo);
        if (cardList == null || cardList.isEmpty()) {
            return null;
        }
        return cardList.get(0);
    }
}
