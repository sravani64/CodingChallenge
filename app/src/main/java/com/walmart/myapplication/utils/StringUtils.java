package com.walmart.myapplication.utils;

/**
 * Created by Abhigna on 5/13/17.
 */

public class StringUtils {


    public static boolean isEmptyOrNUll(final String str){

        return (str==null || str.trim().length() < 1) ? true : false;

    }



}
