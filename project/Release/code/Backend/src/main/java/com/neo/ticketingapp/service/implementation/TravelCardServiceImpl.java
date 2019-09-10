package com.neo.ticketingapp.service.implementation;

import com.neo.ticketingapp.common.enums.BooleanType;
import com.neo.ticketingapp.model.TravelCard;
import com.neo.ticketingapp.repository.TravelCardRepository;
import com.neo.ticketingapp.service.interfaces.TravelCardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelCardServiceImpl  implements TravelCardService {

    private static final Logger logger = LogManager.getLogger(TravelCardServiceImpl.class);

    @Autowired
    TravelCardRepository travelCardRepository;

    @Override
    public TravelCard getTravelCardByNo(String cardNo) throws IllegalAccessException {
        logger.debug("Request to add New User received by the System");
        List<TravelCard> cardList = travelCardRepository.findByCardNo(cardNo);
        if (cardList == null || cardList.isEmpty()) {
            return null;
        }
        return cardList.get(0);
    }

    @Override
    public String insertTravelCard(TravelCard travelCard) throws IllegalAccessException {
        if(getTravelCardByNo(travelCard.getCardNo()) != null)
            return "Travel Card already Exist !";
        travelCardRepository.insert(travelCard);
        return "Travel Card Successfully Added !";
    }

    @Override
    public String updateTravelCardActiveStatus(TravelCard travelCard) throws IllegalAccessException {
        TravelCard updatedTravelCard;
        if((updatedTravelCard = getTravelCardByNo(travelCard.getCardNo())) == null)
            return "Travel Card does not Exist !";
        updatedTravelCard.setActive(travelCard.isActive());
        travelCardRepository.save(updatedTravelCard);
        return "Travel Card Successfully Updated !";
    }

    @Override
    public void deleteTravelCard(String cardNo) throws IllegalAccessException {
        logger.debug("Request received to delete the {}", cardNo);
        TravelCard travelCard = getTravelCardByNo(cardNo);
        travelCardRepository.delete(travelCard);
        logger.info("{} is successfully deleted", cardNo);
    }

    @Override
    public List<TravelCard> getAllTravelCards(String activeStatus) {
        if(activeStatus.equals(BooleanType.True.toString())){
            return travelCardRepository.findByActive(true);
        }else if (activeStatus.equals(BooleanType.False.toString())){
            return travelCardRepository.findByActive(false);
        }else{
            return travelCardRepository.findAll();
        }
    }
}
