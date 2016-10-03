package com.robertmm.mvpdroid.models;

import android.app.Application;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.robertmm.mvpdroid.ApplicationClass;
import com.robertmm.mvpdroid.entities.Album;
import com.robertmm.mvpdroid.utils.Callback;
import com.robertmm.mvpdroid.utils.GsonRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class AlbumListModelImpl implements AlbumListModel {
    private static final String TAG = "AlbumListModelImpl";

    private String albumUrl = "http://jsonplaceholder.typicode.com/albums";
    private ApplicationClass applicationClass;

    @Inject
    public AlbumListModelImpl(Application applicationClass) {
        this.applicationClass = (ApplicationClass) applicationClass;
    }


    @Override
    public void getAllAlbums(final Callback<List<Album>> callback) {

        GsonRequest<Album[]> albumsRequest = new GsonRequest<Album[]>(albumUrl, Album[].class, null,
                new Response.Listener<Album[]>() {
                    @Override
                    public void onResponse(Album[] response) {
                        Log.d(TAG, "onResponse: Response OK");
                        callback.success(new ArrayList<Album>(Arrays.asList(response)));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.failure("Response error");

            }
        });

        applicationClass.addToRequestQueue(albumsRequest, TAG);

    }
}
