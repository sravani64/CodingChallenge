package com.walmart.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.walmart.myapplication.R;
import com.walmart.myapplication.beans.ErrorBean;
import com.walmart.myapplication.beans.Item;
import com.walmart.myapplication.beans.NewsFeeds;
import com.walmart.myapplication.network.NetworkListener;
import com.walmart.myapplication.network.utils.NewsFeedConstants;
import com.walmart.myapplication.network.utils.NewsFeedNetworkHelper;
import com.walmart.myapplication.persistance.DataStorage;
import com.walmart.myapplication.ui.fragments.BusinessNewsFeedsFragment;
import com.walmart.myapplication.ui.fragments.EntertainmentNewsFeedsFragment;
import com.walmart.myapplication.ui.fragments.MasterFragment;
import com.walmart.myapplication.ui.fragments.TopNewsFeedsFragment;
import com.walmart.myapplication.ui.fragments.utils.FragmentUtils;
import com.walmart.myapplication.utils.AppUtils;

import java.util.ArrayList;

public class NewsFeedActivity extends FootLockerBaseActivity
        implements NetworkListener,MasterFragment.IFragmentInteractionListener  {

    public static final String TAG = NewsFeedActivity.class.getSimpleName();
    NewsFeedsPagerAdapter newsAdapterViewPager;
    static ArrayList<MasterFragment> fragmentList = new ArrayList<>();
    static NetworkListener sListener;
    static Context sContext;
    String newType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sListener = this;
        sContext = this;
        showProgressDialog(this);
        initUI();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initUI() {

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        setupFragments();
        final String titles[] = {
                getResources().getString(R.string.first_fragment_title),
                getResources().getString(R.string.second_fragment_title),
                getResources().getString(R.string.third_fragment_title)
        };
        newsAdapterViewPager = new NewsFeedsPagerAdapter(getSupportFragmentManager(),titles);
        newsAdapterViewPager.setFragmentPages(fragmentList);
        vpPager.setAdapter(newsAdapterViewPager);
        vpPager.addOnPageChangeListener(new PageChangeListener());

        initNewsFeedServiceCall(NewsFeedConstants.NewsFeedType.TOP_STORIES);
    }




    public static void initNewsFeedServiceCall(final String newsType){

        /*
         * Check for Cached data here, if existed update Master fragment that data available
         * If not existed make network call
         */
        Object newsData = DataStorage.getInstance().getDataFromCache(newsType);
        if(newsData != null ){
            notifyMasterFragment(newsData);
            return;
        }
        if(!AppUtils.isNetworkConnected(sContext)) {
            ((FootLockerBaseActivity)sContext).showToast(R.string.no_network_message);
            return;
        }
        NewsFeedNetworkHelper newsFeedHelper = new NewsFeedNetworkHelper(sListener);
        newsFeedHelper.getNewsFeeds(newsType);

    }


    private void setupFragments() {

        fragmentList.clear();
        MasterFragment topStoriesFragment = TopNewsFeedsFragment.newInstance(new Bundle());
        MasterFragment businessNewsFragment = BusinessNewsFeedsFragment.newInstance(new Bundle());
        MasterFragment entertainmentNewsFragment = EntertainmentNewsFeedsFragment.newInstance(new Bundle());
        fragmentList.add(topStoriesFragment);
        fragmentList.add(businessNewsFragment);
        fragmentList.add(entertainmentNewsFragment);
    }

    @Override
    public void onFailure(Object data) {
        ErrorBean error = (ErrorBean) data;
        Log.i(TAG, "Error Message "+error.getErrorMessage());
        hideProgressDialog();
        showToast(R.string.no_feeds_error);
    }

    @Override
    public void onSuccess(Object data) {
        NewsFeeds newsFeeds = (NewsFeeds)data;
        //If no data received give handle to failure function
        if(newsFeeds == null || newsFeeds.getItems() == null || newsFeeds.getItems().size() < 0) {
            onFailure(new ErrorBean());
            return;
        }
        Log.i(TAG, "Response Size:::"+newsFeeds.getItems().size());
        //Notify Data received event to Master Fragment
         notifyMasterFragment(newsFeeds);

        //Cache data into Singleton class
        //TODO: Storage should be depends on the business needs.
        DataStorage.getInstance().addDataToCache(FragmentUtils.getNewsFeedType(),newsFeeds);

        hideProgressDialog();
    }

    /*
    * This will notify to MasterFragment that data received
    * */
    private static void notifyMasterFragment(final Object newsFeeds) {

        if(FragmentUtils.getCurrentFragment() == null) {
            fragmentList.get(0).notifyDataChanged(newsFeeds);
        }
        else {
            ((MasterFragment)FragmentUtils.getCurrentFragment()).notifyDataChanged(newsFeeds);
        }
    }

    /**
     * Call back method to receive when List Item clicked
     * @param item : Selected ListItem Object
     */
    @Override
    public void onListItemSelected(Item item) {
        //Launch details Activity
        Intent contentIntent = new Intent(this,NewsContentActivity.class);
        contentIntent.putExtra(UIConstants.KEY_NEWS_CONTENT_EXTRA,item);
        startActivity(contentIntent);


    }

    /**
     * static inner class for listening view pager pages changes
     */
    static class PageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.i(TAG,"onPageScrolled "+position);
        }

        @Override
        public void onPageSelected(int position) {
            Log.i(TAG,"onPageSelected "+position);
            FragmentUtils.setCurrentFragment(fragmentList.get(position));
            initNewsFeedServiceCall(FragmentUtils.getNewsFeedType());
            /*switch (position){
                case 0:

                    break;
                case 1:
                    initNewsFeedServiceCall(NewsFeedConstants.NewsFeedType.BUSINESS);
                    break;
                case 2:
                    initNewsFeedServiceCall(NewsFeedConstants.NewsFeedType.ENTERTAINMENT);
                    break;
                default:
                    break;

            }*/
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Log.i(TAG,"onPageScrollStateChanged "+state);
        }
    }


}
