package com.robertmm.mvpdroid.models;

import android.util.Log;

import com.robertmm.mvpdroid.entities.Album;
import com.robertmm.mvpdroid.utils.Callback;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by roberto on 1/26/16.
 */
public class AlbumListModelImp implements AlbumListModel {
    private static final String TAG = "AlbumListModelImp";

    @Override
    public void getAllAlbums(Callback<List<Album>> callback) {
        List<Album> list = new ArrayList<>();
        list.add(new Album(1, 1, "Title"));
        list.add(new Album(2, 1, "Title2"));
        list.add(new Album(3, 1, "Title3"));
        Log.d(TAG, "getAllAlbums: "+list.size());
        callback.success(list);
    }
}
