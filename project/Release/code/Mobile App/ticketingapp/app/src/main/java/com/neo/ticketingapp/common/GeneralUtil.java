package com.neo.ticketingapp.common;

import android.content.Context;
import android.widget.Toast;

public class GeneralUtil {

    public static Toast toastShort(String text, Context baseContext){
        return Toast.makeText(baseContext, text,Toast.LENGTH_SHORT);
    }
}
