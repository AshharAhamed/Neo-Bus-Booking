package com.neo.ticketingapp.service;

import com.neo.ticketingapp.enums.PassengerType;
import com.neo.ticketingapp.model.Passenger;
import com.neo.ticketingapp.repository.PassengerRepository;
import com.neo.ticketingapp.service.interfaces.PassengerService;
import com.neo.ticketingapp.validation.GeneralUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private GeneralUtils generalUtils;

    public PassengerServiceImpl() {
        this.generalUtils = new GeneralUtils();
    }

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public String insertPassenger(Passenger passenger){
        logger.debug("Request to add New User received by the System");
        String result;
        if((getPassengerByCardId(passenger.getCardNo())) != null)
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
        if(passenger.getType().equals(PassengerType.Local) && passenger.getNic() == null)
            return "Local Account should have a NIC";
        if(passenger.getType().equals(PassengerType.Foreign) && passenger.getPassport() == null)
            return "Foreign Account should have a Passport ID";

        passengerRepository.insert(passenger);
        logger.info("New Passenger {} Added", passenger.getFirstName() + passenger.getLastName());
        return "Passenger added Successfully !";
    }

    @Override
    public String updatePassengerDetails(Passenger passenger) {
        logger.debug("Request to Update {} received by the System", passenger.getCardNo());
        Passenger passengerById = getPassengerByCardId(passenger.getCardNo());
        if((passengerById = getPassengerByCardId(passenger.getCardNo())) != null){
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
        }else {
            return "Card ID does not exist !";
        }
    }

    @Override
    public Passenger getPassengerByCardId(String cardID){
        logger.debug("Request received to get the Passenger with Card Id - {}", cardID);
        List<Passenger> passengerList = passengerRepository.findByCardNo(cardID);
        if (passengerList == null || passengerList.size() == 0) {
            return null;
        }
        return passengerList.get(0);
    }

    @Override
    public String deletePassenger(String cardID) throws IllegalAccessException {
        Passenger passengerById = getPassengerByCardId(cardID);
        if(passengerById != null){
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
}
