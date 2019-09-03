package com.neo.ticketingapp.service.implementation;

import com.neo.ticketingapp.enums.PassengerType;
import com.neo.ticketingapp.model.Card;
import com.neo.ticketingapp.model.Passenger;
import com.neo.ticketingapp.repository.CardRepository;
import com.neo.ticketingapp.repository.PassengerRepository;
import com.neo.ticketingapp.service.interfaces.CardService;
import com.neo.ticketingapp.service.interfaces.PassengerService;
import com.neo.ticketingapp.validation.GeneralUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PassengerServiceImpl implements PassengerService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private GeneralUtils generalUtils;

    @Autowired
    private CardService cardService;

    public PassengerServiceImpl() {
        this.generalUtils = new GeneralUtils();
    }

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public String insertPassenger(Passenger passenger) {
        logger.debug("Request to add New Passenger received by the System");
        String result;
        if ((getPassengerByCardNo(passenger.getCardNo())) != null)
            return "Card already exist !";
        if (!(result = generalUtils.isName(passenger.getFirstName(), "First Name")).equals("Valid"))
            return result;
        if (!(result = generalUtils.isName(passenger.getLastName(), "Last Name")).equals("Valid"))
            return result;
        if (!(result = generalUtils.isEmail(passenger.getEmail())).equals("Valid"))
            return result;
        if (!(result = generalUtils.isPhone(passenger.getContact())).equals("Valid"))
            return result;
        if (!(result = generalUtils.isCardNo(passenger.getCardNo())).equals("Valid"))
            return result;
        if (passenger.getType().equals(PassengerType.Local) && passenger.getNic() == null)
            return "Local Account should have a NIC";
        if (passenger.getType().equals(PassengerType.Foreign) && passenger.getPassport() == null)
            return "Foreign Account should have a Passport ID";

        passengerRepository.insert(passenger);
        logger.info("New Passenger {} Added", passenger.getFirstName() + passenger.getLastName());
        return "Passenger added Successfully !";
    }

    @Override
    public String updatePassengerDetails(Passenger passenger) {
        logger.debug("Request to Update {} received by the System", passenger.getCardNo());
        Passenger passengerById = getPassengerByCardNo(passenger.getCardNo());
        if ((passengerById = getPassengerByCardNo(passenger.getCardNo())) != null) {
            String result;
            if (!(result = generalUtils.isEmail(passenger.getEmail())).equals("Valid"))
                return result;
            if (!(result = generalUtils.isPhone(passenger.getContact())).equals("Valid"))
                return result;
            passengerById.setEmail(passenger.getEmail());
            passengerById.setContact(passenger.getContact());
            passengerRepository.save(passengerById);
            logger.info("New details are updated for the {}", passenger.getFirstName() + passenger.getLastName());
            return "Passenger Updated Successfully !";
        } else {
            return "Card ID does not exist !";
        }
    }

    @Override
    public Passenger getPassengerByCardNo(String cardID) {
        logger.debug("Request received to get the Passenger with Card Id - {}", cardID);
        List<Passenger> passengerList = passengerRepository.findByCardNo(cardID);
        if (passengerList == null || passengerList.size() == 0) {
            return null;
        }
        return passengerList.get(0);
    }

    @Override
    public String deletePassenger(String cardID) throws IllegalAccessException {
        Passenger passengerById = getPassengerByCardNo(cardID);
        if (passengerById != null) {
            passengerRepository.delete(passengerById);
            logger.info("{} is successfully deleted", cardID);
            return cardID + "Successfully Deleted !";
        }
        logger.info("{} not Found", cardID);
        return cardID + "not Found !";
    }

    @Override
    public Passenger logUser(String username, String password) throws IllegalAccessException {
        return null;
    }

    @Override
    public List<Passenger> getAllPassengers(String userType) {
        if (userType.toString().equals("All"))
            return passengerRepository.findAll();
        else if (PassengerType.Local.toString().equals(userType))
            return passengerRepository.findByType(PassengerType.Local);
        else if (PassengerType.Foreign.toString().equals(userType))
            return passengerRepository.findByType(PassengerType.Foreign);
        return null;
    }

    public Passenger logPassenger(String cardNo, String nic) throws IllegalAccessException {
        logger.debug("Request received to logging to the system by {}", cardNo);

        if (cardNo.isEmpty() || nic.isEmpty()) {
            return null;
        }

        Passenger passenger;
        if ((passenger = getPassengerByCardNo(cardNo)) != null) {
            if (passenger.getNic().equals(nic)) {
                return passenger;
            } else if (passenger.getPassport().equals(nic)) {
                return passenger;
            }
        }
        return null;
    }

    @Override
    public Passenger getPassenger(String cardNo) {
        return getPassengerByCardNo(cardNo);
    }

    @Override
    public Passenger getPassengerAccount(String cardNo) {
        Passenger passenger = getPassengerByCardNo(cardNo);
        return passenger;
    }

    @Override
    public String addCard(String travelCardNo, Card card) throws IllegalAccessException {
        Passenger passenger = getPassengerByCardNo(travelCardNo);
        if(cardService.getCardByCardNo(card.getCardNo()) == null) {
            List<Card> cardList = new ArrayList<Card>();
            if ((cardList = passenger.getCardList()).isEmpty()) {
                cardList = new ArrayList<Card>();
            }
            cardList.add(card);
            passenger.setCardList(cardList);
            passengerRepository.save(passenger);
            cardService.insertCard(card);
            return "Card added Successfully !";
        }else
            return "Card Already Exist !";
    }

    @Override
    public String deleteCard(String travelCardNo, String cardNo){
        Passenger passenger = getPassengerByCardNo(travelCardNo);
        if(cardService.getCardByCardNo(cardNo) != null) {
            List<Card> cardList = new ArrayList<Card>();
            if ((cardList = passenger.getCardList()).isEmpty()) {
                cardList = new ArrayList<Card>();
            }
            int index = 0;
            for (Card cardTemp: cardList) {
                if(cardTemp.getCardNo().equals(cardNo)) {
                    cardList.remove(index);
                    break;
                }
                ++index;
            }
            passenger.setCardList(cardList);
            passengerRepository.save(passenger);
            cardService.deleteCard(cardNo);
            return "Card deleted Successfully !";
        }else
            return "Card does not Exist !";
    }

    @Override
    public ArrayList<String> getCards(String travelCardNo){
        Passenger passenger = getPassengerByCardNo(travelCardNo);
        List<Card> cardList = new ArrayList<Card>();
        ArrayList<String> cardNameList = new ArrayList<String>();

        if ((cardList = passenger.getCardList()).isEmpty()) {
            cardList = new ArrayList<Card>();
        }
        for (Card cardTemp: cardList) {
            cardNameList.add(cardTemp.getCardNo());
        }
        return cardNameList;
    }

    @Override
    public String topUp(String travelCardNo, String paymentCardNo, double amount){
        Passenger passenger = getPassengerByCardNo(travelCardNo);
        if(passenger != null){
            if(cardService.getCardByCardNo(paymentCardNo) != null){
                passenger.setCreditBalance(passenger.getCreditBalance() + amount);
                passengerRepository.save(passenger);
                return "Account Topped Up Succesfully !";
            }
            return "Payment Card not Found !";
        }
        return "Passenger not Found !";
    }
}
