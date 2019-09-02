package com.neo.ticketingapp.validation;

import java.security.MessageDigest;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneralUtils {

    public GeneralUtils(){}

    public String isName(String Name, String AttributeName){
        String regex = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(Name);
        if(matcher.find())
            return "Valid";
        else
            return AttributeName + " is Invalid";
    }

    public String isEmail(String email) {
        String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches())
            return "Valid";
        else
            return "Email is Invalid !";
    }

    public String isPhone(String phone){
        String regex = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        if(matcher.matches())
            return "Valid";
        else
            return "Phone is Invalid !";
    }

    public String comparePassword(String newPassword1, String newPassword2){
        if(newPassword1.equals(newPassword2))
            return "Valid";
        else
            return "Passwords do not Match !";
    }

    public String isPassword(String password){
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if(password.matches(pattern))
            return "Valid";
        else
            return "Enter a Combination of Uppercase, Lowercase, Digits, and Special Characters of 8 character at least !";
    }

    public String encryptPassword(String password) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Add password bytes to digest
            md.update(password.getBytes());
            // Get the hash's bytes
            byte[] bytes = md.digest();
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public String isCardNo(String cardNo){
        String regex = "^[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cardNo);
        if(matcher.matches())
            return "Valid";
        else
            return "Card is Invalid !";
    }
}
