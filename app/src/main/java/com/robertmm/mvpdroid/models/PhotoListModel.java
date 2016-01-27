package com.robertmm.mvpdroid.models;

import com.robertmm.mvpdroid.entities.Photo;
import com.robertmm.mvpdroid.utils.Callback;

import java.util.List;

/**
 * Created by roberto on 1/26/16.
 */
public interface PhotoListModel {
    void getAllPhotosFromAlbum(Callback<List<Photo>> callback);
}
