package com.robertmm.mvpdroid.ui.activities;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.robertmm.mvpdroid.R;
import com.robertmm.mvpdroid.utils.ConectivityBroadcastReceiver;


public abstract class BaseActivity extends AppCompatActivity {
    ViewGroup mainFrame;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       enableConectivityBroadcastReceiver();
        setContentView(R.layout.main_base_actvt);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpToolbar();
        mainFrame = (FrameLayout) findViewById(R.id.activity_content);
        getLayoutInflater().inflate(getLayout(), mainFrame);
    }

    abstract Integer getLayout();

    private void enableConectivityBroadcastReceiver(){
        ComponentName receiver = new ComponentName(this, ConectivityBroadcastReceiver.class);
        PackageManager pm = this.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
    private void disableConectivityBroadcastReceiver(){
        ComponentName receiver = new ComponentName(this, ConectivityBroadcastReceiver.class);
        PackageManager pm = this.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.isFinishing()) {
            disableConectivityBroadcastReceiver();
        }

    }

    private void setUpToolbar(){
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
