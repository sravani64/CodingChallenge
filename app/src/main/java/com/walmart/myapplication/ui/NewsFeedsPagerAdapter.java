package com.walmart.myapplication.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.walmart.myapplication.ui.fragments.MasterFragment;

import java.util.ArrayList;

/**
 * Created by sravani on 5/13/17.
 */

public class NewsFeedsPagerAdapter extends FragmentPagerAdapter {

    public static final int PAGES_COUNT = 3;

    public ArrayList<MasterFragment> mNewsFeedFragments;
    String[] mPageTitles;

    public NewsFeedsPagerAdapter(FragmentManager fragmentManager, final String[] pageTitles){
        super(fragmentManager);
        mPageTitles = pageTitles;
    }

    public void setFragmentPages(ArrayList<MasterFragment> fragmentPages){
        mNewsFeedFragments = fragmentPages;
    }

    @Override
    public Fragment getItem(int position) {

       if(null != mNewsFeedFragments && mNewsFeedFragments.size() > position){
           return mNewsFeedFragments.get(position);
       }
        else {
           return null;
       }
    }

    @Override
    public int getCount() {
        return PAGES_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return mPageTitles[position];
    }
}
