package com.walmart.myapplication.persistance;

import java.util.HashMap;

/**
 * Created by sravani on 5/12/17.
 */

/**
 * This follows Singleton Pattern
 */
public class DataStorage {
    private static DataStorage ourDataStorageInstance;

    HashMap<String,Object> dataStorageMap = new HashMap<>();

    public static DataStorage getInstance() {
        if(ourDataStorageInstance == null){
            ourDataStorageInstance = new DataStorage();
        }
        return ourDataStorageInstance;
    }

    private DataStorage() {
    }


    public void addDataToCache(final String key , final Object data){
        dataStorageMap.put(key,data);
    }

    public Object getDataFromCache(final String key){
        return dataStorageMap.get(key);
    }

}
