package com.neo.ticketingapp.common;

import android.content.Context;
import android.widget.Toast;

import com.neo.ticketingapp.common.constants.CommonConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


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

    public static String convertMongoDate(String val) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+0000'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            return outputFormat.format(inputFormat.parse(val));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String convertMongoDateTime(String val) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+0000'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yy/MM/dd HH:mm");
        try {
            outputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));outputFormat.calendar
            return outputFormat.format(inputFormat.parse(val));
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public Retrofit getRetroFit() {
        return new Retrofit.Builder()
                .baseUrl(CommonConstant.SERVER_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
