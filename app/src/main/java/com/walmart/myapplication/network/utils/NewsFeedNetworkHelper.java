package com.walmart.myapplication.network.utils;

import com.walmart.myapplication.beans.ErrorBean;
import com.walmart.myapplication.beans.NewsFeeds;
import com.walmart.myapplication.network.NetworkListener;
import com.walmart.myapplication.network.RetrofitNetworkClient;
import com.walmart.myapplication.utils.AppUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFeedNetworkHelper {
        NetworkListener mNetworkListener;
    
        public NewsFeedNetworkHelper(NetworkListener listener) {
            mNetworkListener = listener;
        }
    
        public NewsFeedNetworkHelper() {
    
        }
    
        public void setNetworkListener(NetworkListener listener){
            mNetworkListener = listener;
        }
    
    
        public void getNewsFeeds(final String newsType) {

           Call<NewsFeeds> callback =  RetrofitNetworkClient.getService().getNewsFeedsList(newsType);
    
            callback.enqueue(new Callback<NewsFeeds>() {
                @Override
                public void onResponse(Call<NewsFeeds> call, Response<NewsFeeds> response) {
                    if(mNetworkListener != null) {
                        NewsFeeds newsFeed =  response.body();
                        mNetworkListener.onSuccess(newsFeed);
                    }
                }
    
                @Override
                public void onFailure(Call<NewsFeeds> call, Throwable t) {
                    if(mNetworkListener != null) {
                        mNetworkListener.onFailure(new ErrorBean(NetworkListener.DEFAULT_ERROR_MESSAGE));
                    }
                }
            });
        }
    
    
    }