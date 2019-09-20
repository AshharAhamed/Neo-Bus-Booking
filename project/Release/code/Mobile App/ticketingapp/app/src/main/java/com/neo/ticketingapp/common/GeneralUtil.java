package com.neo.ticketingapp.common;

import android.content.Context;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class GeneralUtil {

    private static GeneralUtil generalUtil = null;

    private String travelCardID;

    private GeneralUtil() {
    }

    public static GeneralUtil getGeneralUtilInstance() {
        if (generalUtil == null) {
            generalUtil = new GeneralUtil();
        }
        return generalUtil;
    }

    public static Toast toastShort(String text, Context baseContext) {
        return Toast.makeText(baseContext, text, Toast.LENGTH_SHORT);
    }

    public String getTravelCardID() {
        return travelCardID;
    }

    public void setTravelCardID(String travelCardID) {
        this.travelCardID = travelCardID;
    }

    public static String convertMongoDate(String val){
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+0000'");
        SimpleDateFormat outputFormat= new SimpleDateFormat("yyyy/MM/dd");
        try {
            String finalStr = outputFormat.format(inputFormat.parse(val));
            System.out.println(finalStr);
            return finalStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String convertMongoDateTime(String val){
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+0000'");
        SimpleDateFormat outputFormat= new SimpleDateFormat("yy/MM/dd HH:mm");
        try {
            String finalStr = outputFormat.format(inputFormat.parse(val));
            System.out.println(finalStr);
            return finalStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
