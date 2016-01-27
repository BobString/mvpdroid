package com.robertmm.mvpdroid.models;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.robertmm.mvpdroid.ApplicationClass;
import com.robertmm.mvpdroid.entities.Album;
import com.robertmm.mvpdroid.entities.Photo;
import com.robertmm.mvpdroid.utils.Callback;
import com.robertmm.mvpdroid.utils.GsonRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by roberto on 1/26/16.
 */
public class PhotoListModelImpl implements PhotoListModel {
    private static final String TAG = "PhotoListModelImpl";

    private String photoUrl = "http://jsonplaceholder.typicode.com/photos?albumId=";
    private int albumId;
    private ApplicationClass applicationClass;

    public PhotoListModelImpl(int albumId) {
        this.applicationClass = ApplicationClass.getInstance();
        this.albumId = albumId;

    }


    @Override
    public void getAllPhotosFromAlbum(final Callback<List<Photo>> callback) {
        Log.d(TAG, "getAllPhotosFromAlbum: "+photoUrl +albumId);

        GsonRequest<Photo[]> albumsRequest = new GsonRequest<Photo[]>(photoUrl +albumId, Photo[].class,null,
                new Response.Listener<Photo[]>() {
            @Override
            public void onResponse(Photo[] response) {
                Log.d(TAG, "onResponse: Response OK");
                callback.success(new ArrayList<Photo>(Arrays.asList(response)));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.failure("Response error");

            }
        });

        applicationClass.addToRequestQueue(albumsRequest,TAG);

    }
}
