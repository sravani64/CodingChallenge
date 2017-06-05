package com.walmart.myapplication.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;



public class FootLockerBaseActivity extends AppCompatActivity {

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     *  Shows Toast Message
     * @param id : message Id to show.
     */
    public void showToast(final int id) {

        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

    }

    /**
     * Shows Toast Message
     * @param message: String message to show.
     */
    public void showToast(final String message) {

        Toast.makeText(this, message , Toast.LENGTH_SHORT).show();

    }

    /**
     * Shows  progress dailog with loading message
     * If activity is in finishing state or in destroying state then progress wont be shown.
     * @param context
     */
    public void showProgressDialog( Context context) {
        if( !this.isFinishing() ) {
            mProgressDialog = new ProgressDialog(context);
            //mProgressDialog = ProgressDialog.show(context, "", " ", false);
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMessage("Fetching NewsFeeds");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.show();
        }
    }

    /**
     * Hide progress dialog that shown earlier.
     */
    public void hideProgressDialog() {

        if( null != mProgressDialog && mProgressDialog.isShowing()
                && !isFinishing() ) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }

    }


}
