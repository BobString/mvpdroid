package com.robertmm.mvpdroid;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.robertmm.mvpdroid.injection.components.ApplicationComponent;
import com.robertmm.mvpdroid.injection.components.DaggerApplicationComponent;
import com.robertmm.mvpdroid.injection.components.DaggerDatabaseComponent;
import com.robertmm.mvpdroid.injection.components.DatabaseComponent;
import com.robertmm.mvpdroid.injection.modules.AppModule;
import com.robertmm.mvpdroid.injection.modules.DatabaseModule;

public class ApplicationClass extends Application {

    private static final String TAG = "ApplicationClass";
    private RequestQueue requestQueue;
    private ApplicationComponent applicationComponent;

    private DatabaseComponent databaseComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .build();
        applicationComponent.inject(this);

        databaseComponent = DaggerDatabaseComponent.builder()
                .appModule(new AppModule(this))
                .databaseModule(new DatabaseModule())
                .build();
    }


    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public DatabaseComponent getDatabaseComponent(){
        return databaseComponent;
    }

    public Application getInstance(){
        return applicationComponent.application();
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String tag) {
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(request);
    }

    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(TAG);
        getRequestQueue().add(request);
    }

    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }
}
