package com.walmart.myapplication.network;

import com.walmart.myapplication.beans.ErrorBean;
import com.walmart.myapplication.beans.NewsFeeds;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sravani on 5/12/17.
 */
public interface NewsFeedNetworkServiceInterface {

    @GET("/jvanaria/jvanaria.github.io/master/{feed_type}.json")
    Call<NewsFeeds> getNewsFeedsList(@Path("feed_type") String feed_type);



}
