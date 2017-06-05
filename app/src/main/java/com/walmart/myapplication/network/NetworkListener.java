package com.walmart.myapplication.network;

/**
 * Created by sravani on 5/12/17.
 */

public interface NetworkListener extends ResponseSuccessListener,ResponseErrorListener {

    public static final String DEFAULT_ERROR_MESSAGE = "Sorry, we are unable to process your request.";

}
