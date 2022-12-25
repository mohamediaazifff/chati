package com.mohameddev.yo.utils;

import android.content.Context;
import android.content.Intent;


public class utils {
    public static void intentWithClear(Context fromActivity, Class toActivity,String param,String data) {
        Intent i = new Intent(fromActivity, toActivity);
        i.putExtra(param,data);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        fromActivity.startActivity(i);
    }


    public static void intentWithoutClear(Context fromActivity, Class toActivity, String param, String data) {
        Intent i = new Intent(fromActivity, toActivity);
        if (data==null||param==null){
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            fromActivity.startActivity(i);

            // Toast.makeText(fromActivity, "its look like there is no user id", Toast.LENGTH_SHORT).show();
        }
        else
        i.putExtra(param,data);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        fromActivity.startActivity(i);
    }

    public void get_intent_data(){

    }
}
