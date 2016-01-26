package com.robertmm.mvpdroid.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.robertmm.mvpdroid.R;

/**
 * Created by roberto on 1/26/16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    ViewGroup mainFrame;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_base_actvt);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpToolbar();
        mainFrame = (FrameLayout) findViewById(R.id.activity_content);
        getLayoutInflater().inflate(getLayout(), mainFrame);
    }

    abstract Integer getLayout();

    private void setUpToolbar(){
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
