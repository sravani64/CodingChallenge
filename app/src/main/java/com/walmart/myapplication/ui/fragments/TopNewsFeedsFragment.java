package com.walmart.myapplication.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.walmart.myapplication.beans.Item;
import com.walmart.myapplication.beans.NewsFeeds;

import com.walmart.myapplication.R;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link IFragmentInteractionListener}
 * interface.
 */
public class TopNewsFeedsFragment extends MasterFragment {

    private int mColumnCount = 1;
    private IFragmentInteractionListener mListener;
    NewsFeeds mNewsFeeds;
    NewsItemRecyclerViewAdapter mRecycleAdapter;

    @Override
    public void notifyDataChanged(Object data) {
        mNewsFeeds = (NewsFeeds) data;
        if(null != mRecycleAdapter ) {
            mRecycleAdapter.notifyDataChanged(mNewsFeeds.getItems());
        }
    }

    public TopNewsFeedsFragment() {

    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TopNewsFeedsFragment newInstance(final Bundle bundle) {
        TopNewsFeedsFragment fragment = new TopNewsFeedsFragment();
        fragment.setArguments(bundle);
        fragment.setRetainInstance(true);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            //if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            /*} else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }*/
            List<Item> newsData;
            if(mNewsFeeds == null) {
                newsData = new ArrayList<Item>();
            }
            else {
                newsData = mNewsFeeds.getItems();
            }
            mRecycleAdapter = new NewsItemRecyclerViewAdapter(newsData, mListener);
            recyclerView.setAdapter(mRecycleAdapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IFragmentInteractionListener) {
            mListener = (IFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
