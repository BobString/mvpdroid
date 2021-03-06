package com.robertmm.mvpdroid.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by roberto on 1/26/16.
 */
public class ConectivityBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        boolean isWiFi = false;
        if(isConnected){
            isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
        }

        Toast.makeText(context, "Intent Detected. Is connected? "+isConnected+" Is WiFi? "+ isWiFi, Toast.LENGTH_LONG).show();
    }
}
