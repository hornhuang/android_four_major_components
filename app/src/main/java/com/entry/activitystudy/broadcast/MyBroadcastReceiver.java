package com.entry.activitystudy.broadcast;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.entry.activitystudy.utils.IntentManager;

/**
 *
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    private int progressStatus = 0;
    private final String EXTRA_ID = "status";

    private ProgressDialog progressDialog;

    public MyBroadcastReceiver(ProgressDialog progressDialog){
        this.progressDialog = progressDialog;
    }

    //
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()){
            case IntentManager.ACTION_TYPE_SERVICE:

                break;

            case IntentManager.ACTION_TYPE_THREAD:

                break;
        }
        progressStatus = intent.getIntExtra(EXTRA_ID, 0);
    }

}
