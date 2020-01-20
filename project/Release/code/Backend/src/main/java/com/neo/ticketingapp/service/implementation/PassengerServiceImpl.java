package com.neo.ticketingapp.service.implementation;

import com.neo.ticketingapp.common.constants.CommonConstants;
import com.neo.ticketingapp.common.enums.PassengerType;
import com.neo.ticketingapp.model.*;
import com.neo.ticketingapp.repository.PassengerRepository;
import com.neo.ticketingapp.service.interfaces.*;
import com.neo.ticketingapp.validation.GeneralUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Service
public class PassengerServiceImpl implements PassengerService {
    private static final Logger logger = LogManager.getLogger(PassengerServiceImpl.class);
    private GeneralUtils generalUtils;
    private static final String MESSAGE = "Message";
    private static final String PASSENGER_NOT_FOUND = "Passenger not Found !";
    private static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Autowired
    private CardService cardService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private JourneyPassengerService journeyPassengerService;

    @Autowired
    private JourneyService journeyService;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private PassengerLogService passengerLogService;

    public PassengerServiceImpl() {
        this.generalUtils = new GeneralUtils();
    }

    @Override
    public String insertPassenger(Passenger passenger) {
        logger.debug("Request to add New Passenger received by the System");
        String result;
        if ((getPassengerByCardNo(passenger.getCardNo())) != null)
            return "Card already exist !";
        result = generalUtils.isName(passenger.getFirstName(), "First Name");
        if (!CommonConstants.VALID.equals(result))
            return result;
        result = generalUtils.isName(passenger.getLastName(), "Last Name");
        if (!CommonConstants.VALID.equals(result))
            return result;
        result = generalUtils.isEmail(passenger.getEmail());
        if (!CommonConstants.VALID.equals(result))
            return result;
        result = generalUtils.isPhone(passenger.getContact());
        if (!CommonConstants.VALID.equals(result))
            return result;
        result = generalUtils.isCardNo(passenger.getCardNo());
        if (!CommonConstants.VALID.equals(result))
            return result;
        if (passenger.getType().equals(PassengerType.LOCAL) && passenger.getNic() == null)
            return "Local Account should have a NIC";
        if (passenger.getType().equals(PassengerType.FOREIGN) && passenger.getPassport() == null)
            return "Foreign Account should have a Passport ID";

        passengerRepository.insert(passenger);
        logger.info("New Passenger {} Added", passenger.getFirstName() + passenger.getLastName());
        return "Passenger added Successfully !";
    }

    @Override
    public String updatePassengerDetails(Passenger passenger) {
        logger.debug("Request to Update {} received by the System", passenger.getCardNo());
        Passenger passengerById;
        if ((passengerById = getPassengerByCardNo(passenger.getCardNo())) != null) {
            String result = generalUtils.isEmail(passenger.getEmail());
            if (!CommonConstants.VALID.equals(result))
                return result;
            result = generalUtils.isPhone(passenger.getContact());
            if (!CommonConstants.VALID.equals(result))
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
    public String updatePassengerProfileDetails(Passenger passenger) {
        logger.debug("Request to Update profile details {} received by the System", passenger.getAccountId());
        Passenger dbPassenger;
        if ((dbPassenger = getPassengerByCardNo(passenger.getCardNo())) != null) {
            dbPassenger.setFirstName(passenger.getFirstName());
            dbPassenger.setLastName(passenger.getLastName());
            dbPassenger.setEmail(passenger.getEmail());
            dbPassenger.setContact(passenger.getContact());
            dbPassenger.setNic(passenger.getNic());
            passengerRepository.save(dbPassenger);
            logger.info("New details are updated for the {}", dbPassenger);
            return "Passenger Profile Updated Successfully !";
        }
        return "";
    }

    @Override
    public Passenger getPassengerByCardNo(String cardID) {
        logger.debug("Request received to get the Passenger with Card Id - {}", cardID);
        List<Passenger> passengerList = passengerRepository.findByCardNo(cardID);
        if (passengerList == null || passengerList.isEmpty()) {
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
        if (userType.equals("All"))
            return passengerRepository.findAll();
        else if (PassengerType.LOCAL.toString().equals(userType))
            return passengerRepository.findByType(PassengerType.LOCAL);
        else if (PassengerType.FOREIGN.toString().equals(userType))
            return passengerRepository.findByType(PassengerType.FOREIGN);
        return new ArrayList<>();
    }

    public Passenger logPassenger(String cardNo, String nic) throws IllegalAccessException {
        logger.debug("Request received to logging to the system by {}", cardNo);
        if (!cardNo.isEmpty() || !nic.isEmpty()) {
            Passenger passenger;
            if ((passenger = getPassengerByCardNo(cardNo)) != null
                    && (passenger.getNic().equals(nic) || passenger.getPassport().equals(nic))) {
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
        return getPassengerByCardNo(cardNo);
    }

    @Override
    public String addCard(String travelCardNo, Card card) throws IllegalAccessException {
        Passenger passenger = getPassengerByCardNo(travelCardNo);
        if (cardService.getCardByCardNo(card.getCardNo()) == null) {
            List<Card> cardList = passenger.getCardList();
            cardList.add(card);
            passenger.setCardList(cardList);
            passengerRepository.save(passenger);
            cardService.insertCard(card);
            return "Card added Successfully !";
        } else {
            return "Card Already Exist !";
        }
    }

    @Override
    public String deleteCard(String travelCardNo, String cardNo) {
        Passenger passenger = getPassengerByCardNo(travelCardNo);
        if (cardService.getCardByCardNo(cardNo) != null) {
            List<Card> cardList = passenger.getCardList();
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
        List<Card> cardList = passenger.getCardList();
        ArrayList<String> cardNameList = new ArrayList<>();
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
                if ((paymentService.validatePayment(paymentCardNo, card.getCcNo(), amount))) {
                    passenger.setCreditBalance(passenger.getCreditBalance() + amount);
                    passengerRepository.save(passenger);
                    return "Account Topped Up Successfully !";
                }
                return "Sorry ! Transaction Failed";
            }
            return "Payment Card not Found !";
        }
        return PASSENGER_NOT_FOUND;
    }

    @Override
    public String topUpByCash(String travelCardNo, double amount) {
        Passenger passenger = getPassengerByCardNo(travelCardNo);
        if (passenger != null) {
            passenger.setCreditBalance(passenger.getCreditBalance() + amount);
            passengerRepository.save(passenger);
            return "Account Topped Up Successfully !";
        }
        return PASSENGER_NOT_FOUND;
    }

    @Override
    public Passenger getPassengerByNIC(String nic) {
        logger.debug("Request received to get the Passenger with NIC - {}", nic);
        List<Passenger> passengerList = passengerRepository.findByNic(nic);
        if (passengerList == null || passengerList.isEmpty()) {
            return null;
        }
        return passengerList.get(0);
    }

    @Override
    public Passenger getPassengerByPassport(String passport) {
        logger.debug("Request received to get the Passenger with Passport - {}", passport);
        List<Passenger> passengerList = passengerRepository.findByPassport(passport);
        if (passengerList == null || passengerList.isEmpty()) {
            return null;
        }
        return passengerList.get(0);
    }

    @Override
    public String recoverTravelCard(String nic, String travelCardNo) {
        Passenger passenger;
        if (((passenger = getPassengerByNIC(nic)) != null) || ((passenger = getPassengerByPassport(nic)) != null)) {
            passenger.setCardNo(travelCardNo);
            passengerRepository.save(passenger);
            return "New Travel Card No is " + travelCardNo;
        } else
            return PASSENGER_NOT_FOUND;
    }

    @Override
    public JSONObject validateJourney(String travelCardID, String startStation, String endStation, String journeyID) {
        JSONObject jsonObject = new JSONObject();
        Passenger passenger = getPassengerByCardNo(travelCardID);
        Journey journey = journeyService.getJourneyByJourneyID(journeyID);
        Route route = routeService.getRouteByRouteID(journey.getRouteID());
        double creditBalance = passenger.getCreditBalance();
        double ticketPrice = calculateTicketPrice(route.getBusHalts(), startStation, endStation);
        if (ticketPrice == -1) {
            jsonObject.put(CommonConstants.ERROR, "Start Station is after End Station");
            return jsonObject;
        } else if (ticketPrice == -2) {
            jsonObject.put(CommonConstants.ERROR, "Station does not Exist");
            return jsonObject;
        }
        if ((getBusHaltPosition(route.getBusHalts(), journey.getNextStation())) > getBusHaltPosition(
                route.getBusHalts(), startStation)) {
            jsonObject.put(CommonConstants.ERROR, "Bus has already passed");
            return jsonObject;
        }
        if (creditBalance >= ticketPrice) {
            jsonObject.put(MESSAGE, "Success");
            return jsonObject;
        } else {
            jsonObject.put(CommonConstants.ERROR, "Not Sufficient Credit Balance");
            return jsonObject;
        }
    }

    @Override
    public JSONObject startJourney(String travelCardID, String startStation, String endStation, String journeyID)
            throws IllegalAccessException, ParseException {
        JSONObject jsonObject = new JSONObject();
        Passenger passenger = getPassengerByCardNo(travelCardID);
        journeyPassengerService.addPassenger(journeyID, travelCardID);

        DateFormat dateFormat = new SimpleDateFormat(TIME_PATTERN);
        Date date = new Date();
        String dateString = dateFormat.format(date);
        date = new SimpleDateFormat(TIME_PATTERN).parse(dateString);

        PassengerLog passengerLog = new PassengerLog();
        passengerLog.setTravelCardID(travelCardID);
        passengerLog.setJourneyID(journeyID);
        Journey journey = journeyService.getJourneyByJourneyID(journeyID);
        Route route = routeService.getRouteByRouteID(journey.getRouteID());
        double ticketPrice = calculateTicketPrice(route.getBusHalts(), startStation, endStation);
        passenger.setCreditBalance(passenger.getCreditBalance() - ticketPrice);
        passengerRepository.save(passenger);

        passengerLog.setTicketPrice(ticketPrice);
        passengerLog.setStartStation(startStation);
        passengerLog.setEndStation(endStation);
        passengerLog.setStartTime(date);
        passengerLog = passengerLogService.insertLog(passengerLog);
        jsonObject.put("ticketPrice", ticketPrice);
        jsonObject.put("logID", passengerLog.getLogID());
        jsonObject.put(MESSAGE, "Trip started !");
        return jsonObject;
    }

    @Override
    public String endJourney(String logID) throws IllegalAccessException, ParseException {
        PassengerLog passengerLog = passengerLogService.getLogByLogID(logID);
        DateFormat dateFormat = new SimpleDateFormat(TIME_PATTERN);
        Date date = new Date();
        String dateString = dateFormat.format(date);
        date = new SimpleDateFormat(TIME_PATTERN).parse(dateString);
        passengerLog.setEndTime(date);
        passengerLogService.updateLogDetails(logID, passengerLog);
        journeyPassengerService.removePassenger(passengerLogService.getLogByLogID(logID).getJourneyID(),
                passengerLogService.getLogByLogID(logID).getTravelCardID());
        return CommonConstants.SUCCESS;
    }

    @Override
    public void sendEmail(String code) throws MessagingException, IOException {
        Passenger passenger = getPassengerByCardNo(code);
        if (passenger != null) {
            String email = passenger.getEmail();
            String pwd = passenger.getNic();
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("neoticketingsl@gmail.com", "5Aneo@#123");
                }
            });
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("neoticketingsl@gmail.com", false));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            msg.setSubject("Password Recovery Request in NEO Ticketing ");
            String content = "Dear Sir/ Madam,<br/> This email is sent to because you have requested to recover the password of the NEO BUS App. So, You can find the Recovered Password in this email and if you didn't request the mail Please ignore this email.<br/><b> Your Password : "
                    + pwd + "</b><br/>Thanks and Regards<br/> <b>NEO Team</b> ";
            msg.setContent(content, "text/html");
            msg.setSentDate(new Date());

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(content, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            msg.setContent(multipart);
            Transport.send(msg);
        }
    }

    private double calculateTicketPrice(List<String> busHaltList, String startStation, String endStation) {
        int stations = this.getBusHaltDifference(busHaltList, startStation, endStation);
        if (stations == -1)
            return -1;
        else if (stations == -2)
            return -2;
        else
            return (stations * 15.0);
    }

    private int getBusHaltDifference(List<String> busHaltList, String startStation, String endStation) {
        int startPoint = getBusHaltPosition(busHaltList, startStation);
        int endPoint = getBusHaltPosition(busHaltList, endStation);
        if (startPoint == -1 || endPoint == -1)
            return -2;
        if (startPoint >= endPoint)
            return -1;
        return endPoint - startPoint;
    }

    private int getBusHaltPosition(List<String> busHaltList, String halt) {
        int index = 0;
        for (String busHalt : busHaltList) {
            if (halt.equals(busHalt))
                return index;
            ++index;
        }
        return -1;
    }
}
