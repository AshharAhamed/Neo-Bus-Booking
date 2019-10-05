package com.neo.ticketingapp.service.interfaces;

import com.neo.ticketingapp.model.Card;
import com.neo.ticketingapp.model.Passenger;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface PassengerService {

	String insertPassenger(Passenger passenger) throws IllegalAccessException;

	String updatePassengerDetails(Passenger passenger) throws IllegalAccessException;

	String updatePassengerProfileDetails(Passenger passenger);

	String deletePassenger(String username) throws IllegalAccessException;

	Passenger logUser(String username, String password) throws IllegalAccessException;

	List<Passenger> getAllPassengers(String userType);

	Passenger getPassengerByCardNo(String cardID) throws IllegalAccessException;

	Passenger logPassenger(String cardNo, String nic) throws IllegalAccessException;

	Passenger getPassenger(String cardNo);

	Passenger getPassengerAccount(String cardNo);

	String addCard(String travelCardNo, Card card) throws IllegalAccessException;

	String deleteCard(String travelCardNo, String cardNo);

	List<String> getCards(String travelCardNo);

	String topUp(String travelCardNo, String paymentCardNo, double amount);

	String topUpByCash(String travelCardNo, double amount) throws IllegalAccessException;

	String recoverTravelCard(String nic, String travelCardNo) throws IllegalAccessException;

	Passenger getPassengerByPassport(String passport);

	Passenger getPassengerByNIC(String nic);

	JSONObject startJourney(String travelCardID, String startStation, String endStation, String journeyID)
			throws IllegalAccessException, ParseException;

	JSONObject validateJourney(String travelCardID, String startStation, String endStation, String journeyID);

	String endJourney(String logID) throws IllegalAccessException, ParseException;

	void sendEmail(String code) throws AddressException, MessagingException, IOException;
}
