package com.neo.ticketingapp.common;

import android.content.Context;
import android.widget.Toast;

public class GeneralUtil {

    private static GeneralUtil generalUtil = null;

    private String travelCardID;

    private GeneralUtil(){}

    public static GeneralUtil getGeneralUtilInstance(){
        if(generalUtil == null){
            generalUtil = new GeneralUtil();
        }
        return generalUtil;
    }

    public static Toast toastShort(String text, Context baseContext){
        return Toast.makeText(baseContext, text,Toast.LENGTH_SHORT);
    }

    public String getTravelCardID() {
        return travelCardID;
    }

    public void setTravelCardID(String travelCardID) {
        this.travelCardID = travelCardID;
    }
}
