package com.walmart.myapplication.network;

import com.walmart.myapplication.network.utils.NewsFeedConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sravani on 5/12/17.
 */
public class RetrofitNetworkClient {


    private static Retrofit getRetroInstance(){

        return new Retrofit.Builder().baseUrl(NewsFeedConstants.CONTENT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static NewsFeedNetworkServiceInterface getService(){

        return getRetroInstance().create(NewsFeedNetworkServiceInterface.class);
    }
}
