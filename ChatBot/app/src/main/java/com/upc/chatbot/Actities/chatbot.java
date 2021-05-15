package com.upc.chatbot.Actities;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class chatbot extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
