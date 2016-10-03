package com.robertmm.mvpdroid.models;

import com.robertmm.mvpdroid.entities.Album;
import com.robertmm.mvpdroid.utils.Callback;

import java.util.List;


public interface AlbumListModel {
    void getAllAlbums(Callback<List<Album>> callback);
}
