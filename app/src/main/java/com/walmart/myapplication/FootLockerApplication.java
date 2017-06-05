package com.walmart.myapplication;

import android.app.Application;

import com.walmart.myapplication.persistance.DataStorage;

/**
 * Created by sravani on 5/13/17.
 */

public class FootLockerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initSingletons();
    }

    /*
    * Init all the singleton instances here if any
    * */
    private void initSingletons() {
        DataStorage.getInstance();
    }
}
