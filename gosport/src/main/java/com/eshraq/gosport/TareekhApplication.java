package com.eshraq.gosport;

import android.app.Application;
import android.content.Context;

/**
 * Created by Ahmed on 12/29/2015.
 */
public class TareekhApplication extends Application{
    public static final String LOG_TAG = "Tareekh";

    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }


    public static Context getContext() {
        return context;
    }
}
