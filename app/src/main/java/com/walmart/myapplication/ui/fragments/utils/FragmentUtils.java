package com.walmart.myapplication.ui.fragments.utils;

import android.support.v4.app.Fragment;

import com.walmart.myapplication.network.utils.NewsFeedConstants;
import com.walmart.myapplication.ui.fragments.BusinessNewsFeedsFragment;
import com.walmart.myapplication.ui.fragments.EntertainmentNewsFeedsFragment;
import com.walmart.myapplication.ui.fragments.MasterFragment;
import com.walmart.myapplication.ui.fragments.TopNewsFeedsFragment;

/**
 * Created by sravani on 5/13/17.
 */

public class FragmentUtils {

    static Fragment currentFragment;

    public static Fragment getCurrentFragment(){


        return currentFragment;
    }

    public static void setCurrentFragment(final Fragment fragment){
        currentFragment = fragment;
    }


    /**
     * Returns news feed type URL bases on the current page
     * @return: News Feed URL
     */
    public static String getNewsFeedType(){

        switch (getCurrentPageIndex()){
            case 0:
                return NewsFeedConstants.NewsFeedType.TOP_STORIES;
            case 1:
                return NewsFeedConstants.NewsFeedType.BUSINESS;
            case 2:
                return NewsFeedConstants.NewsFeedType.ENTERTAINMENT;
        }
        return NewsFeedConstants.NewsFeedType.TOP_STORIES;
    }


    private static int getCurrentPageIndex(){

        MasterFragment fragment = (MasterFragment) getCurrentFragment();
        if( fragment == null ) {
            return 0;
        }
        else {
            if(fragment instanceof TopNewsFeedsFragment) {
                return 0;
            }
            else if(fragment instanceof BusinessNewsFeedsFragment) {
                return 1;
            }
            else if(fragment instanceof EntertainmentNewsFeedsFragment) {
                return 2;
            }
        }
        return 0;
    }
}
