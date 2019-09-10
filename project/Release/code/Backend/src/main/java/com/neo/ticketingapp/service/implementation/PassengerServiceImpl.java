package com.neo.ticketingapp.service.implementation;

import com.neo.ticketingapp.enums.PassengerType;
import com.neo.ticketingapp.model.*;
import com.neo.ticketingapp.repository.CardRepository;
import com.neo.ticketingapp.repository.PassengerRepository;
import com.neo.ticketingapp.service.interfaces.*;
import com.neo.ticketingapp.validation.GeneralUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PassengerServiceImpl implements PassengerService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private GeneralUtils generalUtils;

    @Autowired
    private CardService cardService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private JourneyPassengerService journeyPassengerService;

    @Autowired
    private JourneyService journeyService;

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
        if (cardService.getCardByCardNo(card.getCardNo()) == null) {
            List<Card> cardList = new ArrayList<Card>();
            if ((cardList = passenger.getCardList()).isEmpty()) {
                cardList = new ArrayList<Card>();
            }
            cardList.add(card);
            passenger.setCardList(cardList);
            passengerRepository.save(passenger);
            cardService.insertCard(card);
            return "Card added Successfully !";
        } else
            return "Card Already Exist !";
    }

    @Override
    public String deleteCard(String travelCardNo, String cardNo) {
        Passenger passenger = getPassengerByCardNo(travelCardNo);
        if (cardService.getCardByCardNo(cardNo) != null) {
            List<Card> cardList = new ArrayList<Card>();
            if ((cardList = passenger.getCardList()).isEmpty()) {
                cardList = new ArrayList<Card>();
            }
            int index = 0;
            for (Card cardTemp : cardList) {
                if (cardTemp.getCardNo().equals(cardNo)) {
                    cardList.remove(index);
                    break;
                }
                ++index;
            }
            passenger.setCardList(cardList);
            passengerRepository.save(passenger);
            cardService.deleteCard(cardNo);
            return "Card deleted Successfully !";
        } else
            return "Card does not Exist !";
    }

    @Override
    public ArrayList<String> getCards(String travelCardNo) {
        Passenger passenger = getPassengerByCardNo(travelCardNo);
        List<Card> cardList = new ArrayList<Card>();
        ArrayList<String> cardNameList = new ArrayList<String>();

        if ((cardList = passenger.getCardList()).isEmpty()) {
            cardList = new ArrayList<Card>();
        }
        for (Card cardTemp : cardList) {
            cardNameList.add(cardTemp.getCardNo());
        }
        return cardNameList;
    }

    @Override
    public String topUp(String travelCardNo, String paymentCardNo, double amount) {
        Passenger passenger = getPassengerByCardNo(travelCardNo);
        if (passenger != null) {
            Card card;
            if ((card = cardService.getCardByCardNo(paymentCardNo)) != null) {
                PaymentService paymentService = new PaymentServiceImpl();
                if ((paymentService.processPayment(paymentCardNo, card.getCcNo(), amount))) {
                    passenger.setCreditBalance(passenger.getCreditBalance() + amount);
                    passengerRepository.save(passenger);
                    return "Account Topped Up Successfully !";
                }
                return "Sorry ! Transaction Failed";
            }
            return "Payment Card not Found !";
        }
        return "Passenger not Found !";
    }

    @Override
    public String topUpByCash(String travelCardNo, double amount) throws IllegalAccessException {
        Passenger passenger = getPassengerByCardNo(travelCardNo);
        if (passenger != null) {
            passenger.setCreditBalance(passenger.getCreditBalance() + amount);
            passengerRepository.save(passenger);
            return "Account Topped Up Successfully !";
        }
        return "Passenger not Found !";
    }

    @Override
    public Passenger getPassengerByNIC(String NIC) {
        logger.debug("Request received to get the Passenger with NIC - {}", NIC);
        List<Passenger> passengerList = passengerRepository.findByNic(NIC);
        if (passengerList == null || passengerList.size() == 0) {
            return null;
        }
        return passengerList.get(0);
    }

    @Override
    public Passenger getPassengerByPassport(String Passport) {
        logger.debug("Request received to get the Passenger with Passport - {}", Passport);
        List<Passenger> passengerList = passengerRepository.findByPassport(Passport);
        if (passengerList == null || passengerList.size() == 0) {
            return null;
        }
        return passengerList.get(0);
    }

    @Override
    public String recoverTravelCard(String nic, String travelCardNo) throws IllegalAccessException {
        Passenger passenger;
        if ((passenger = getPassengerByNIC(nic)) != null) {
            passenger.setCardNo(travelCardNo);
            passengerRepository.save(passenger);
            return "New Travel Card No is " + travelCardNo;
        } else if ((passenger = getPassengerByPassport(nic)) != null) {
            passenger.setCardNo(travelCardNo);
            passengerRepository.save(passenger);
            return "New Travel Card No is " + travelCardNo;
        } else
            return "Passenger not Found !";
    }

    @Override
    public JSONObject startJourney(String travelCardID, String startStation, String endStation, String journeyID) throws IllegalAccessException {
        JSONObject jsonObject = new JSONObject();
        Passenger passenger = getPassengerByCardNo(travelCardID);
        Journey journey = journeyService.getJourneyByJourneyID(journeyID);
        Route route = routeService.getRouteByRouteID(journey.getRouteID());
        double creditBalance = passenger.getCreditBalance();
        double ticketPrice = calculateTicketPrice(route.getBusHalts(), startStation, endStation);
        if(ticketPrice == -1){
            jsonObject.put("Error", "Start Station is after End Station");
            return jsonObject;
        }else if (ticketPrice == -2){
            jsonObject.put("Error", "Station does not Exist");
            return jsonObject;
        }
        if((getBusHaltPosition(route.getBusHalts(), journey.getNextStation())) > getBusHaltPosition(route.getBusHalts(), startStation)){
            jsonObject.put("Error", "Bus has already passed");
            return jsonObject;
        }
        if(creditBalance >= ticketPrice) {
            creditBalance -= ticketPrice;
            passenger.setCreditBalance(creditBalance);
            passengerRepository.save(passenger);
            journeyPassengerService.addPassenger(journeyID, travelCardID);
            jsonObject.put("Message", "Trip started !");
            return jsonObject;
        }
        else{
            jsonObject.put("Error", "Not Sufficient Credit Balance");
            return jsonObject;
        }
    }

    private double calculateTicketPrice(List<String> busHaltList, String startStation, String endStation){
        int stations = this.getBusHaltDifference(busHaltList, startStation, endStation);
        if(stations == -1)
            return -1;
        else if(stations == -2)
            return -2;
        else
            return (stations * 15.0);
    }

    private int getBusHaltDifference(List<String> busHaltList, String startStation, String endStation){
        int startPoint = getBusHaltPosition(busHaltList, startStation);
        int endPoint = getBusHaltPosition(busHaltList, endStation);
        if(startPoint == -1 || endPoint == -1)
            return -2;
        if(startPoint >= endPoint)
            return -1;
        return endPoint - startPoint;
    }

    private int getBusHaltPosition(List<String> busHaltList, String halt){
        int index = 0;
        for (String busHalt: busHaltList) {
            if(halt.equals(busHalt))
                return index;
            ++index;
        }
        return -1;
    }
}
