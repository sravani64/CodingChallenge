package com.walmart.myapplication.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.walmart.myapplication.beans.Item;
import com.walmart.myapplication.ui.FootLockerBaseActivity;
import com.walmart.myapplication.ui.fragments.utils.FragmentUtils;

/**
 * A simple {@link Fragment} subclass.
 * But super Class for all the Fragments in the application. This can be used to place all the common/generic behaviour
 */
public abstract class MasterFragment extends Fragment implements IFragmentDataListener {


    public abstract void notifyDataChanged(Object data);


    public MasterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDataReceived(Object data) {
        ((MasterFragment) FragmentUtils.getCurrentFragment()).notifyDataChanged(data);

    }

    @Override
    public void onRefresh(Object data) {

    }

    public  interface IFragmentInteractionListener{
        public void onListItemSelected(Item item);
        //public void notifyDataReceived(Object data);
    }


}
