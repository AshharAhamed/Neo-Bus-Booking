package com.neo.ticketingapp.service.interfaces;

import com.neo.ticketingapp.model.Passenger;

import java.util.List;

public interface PassengerService {

    String insertPassenger(Passenger passenger) throws IllegalAccessException;

    String updatePassengerDetails(Passenger passenger) throws IllegalAccessException;

    String deletePassenger(String username) throws IllegalAccessException;

    Passenger logUser(String username, String password) throws IllegalAccessException;

    List<Passenger> getAllPassengers(String userType);

    public Passenger getPassengerByCardNo(String cardID) throws IllegalAccessException;

    Passenger logPassenger(String cardNo, String nic) throws IllegalAccessException;

    public Passenger getPassenger(String cardNo);

    public Passenger getPassengerAccount(String cardNo);
}
