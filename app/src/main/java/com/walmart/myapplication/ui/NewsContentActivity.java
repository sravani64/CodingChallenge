package com.walmart.myapplication.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


import com.walmart.myapplication.R;
import com.walmart.myapplication.beans.Item;
import com.walmart.myapplication.utils.StringUtils;
import com.squareup.picasso.Picasso;


public class NewsContentActivity extends FootLockerBaseActivity {

    TextView mNewsTitleTV;
    ImageView mNewsImageTV;
    TextView mNewsContentTV;

    //TODO: Its better to use Parcellable instead of Serialzable
    Item mNewsContentPOJO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        initUI();
    }

    /**
     * Init method to initialize all the UI elements
     */
    private void initUI() {
        mNewsTitleTV = (TextView) findViewById(R.id.title);
        mNewsImageTV = (ImageView) findViewById(R.id.contentImage);
        mNewsContentTV = (TextView) findViewById(R.id.content);

        mNewsContentPOJO = (Item) getIntent().getSerializableExtra(UIConstants.KEY_NEWS_CONTENT_EXTRA);

        if( null != mNewsContentPOJO) {
            if(!StringUtils.isEmptyOrNUll(mNewsContentPOJO.getTitle())){
                mNewsTitleTV.setText(mNewsContentPOJO.getTitle());
            }
            else {
                mNewsTitleTV.setText("");
            }
            if(!StringUtils.isEmptyOrNUll(mNewsContentPOJO.getDescription())){
                mNewsContentTV.setText(mNewsContentPOJO.getDescription());
            }
            else {
                mNewsContentTV.setText("");
            }
            if(!StringUtils.isEmptyOrNUll(mNewsContentPOJO.getThumbnailUrl())) {
                Picasso.with(this).load(mNewsContentPOJO.getThumbnailUrl()).into(mNewsImageTV);
            }

        }

    }


}
