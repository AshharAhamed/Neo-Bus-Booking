package com.neo.ticketingapp.validation;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneralUtils {
    private static final String VALID = "Valid";
    private static final Logger logger = LogManager.getLogger(GeneralUtils.class);


    public String isName(String name, String attributeName) {
        String regex = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        if (matcher.find())
            return VALID;
        else
            return attributeName + " is Invalid";
    }

    public String isEmail(String email) {
        String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches())
            return VALID;
        else
            return "Email is Invalid !";
    }

    public String isPhone(String phone) {
        String regex = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches())
            return VALID;
        else
            return "Phone is Invalid !";
    }

    public String comparePassword(String newPassword1, String newPassword2) {
        if (newPassword1.equals(newPassword2))
            return VALID;
        else
            return "Passwords do not Match !";
    }

    public String isPassword(String password) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if (password.matches(pattern))
            return VALID;
        else
            return "Enter a Combination of Uppercase, Lowercase, Digits, and Special Characters of 8 character at least !";
    }

    public String encryptPassword(String password) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (Exception e) {
            logger.log(Level.valueOf("context"), e);
        }
        return generatedPassword;
    }

    public String isCardNo(String cardNo) {
        String regex = "^[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cardNo);
        if (matcher.matches())
            return VALID;
        else
            return "Card is Invalid !";
    }

    public String isUsername(String userName) {
        return VALID;
    }
}
