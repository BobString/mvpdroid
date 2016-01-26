package com.robertmm.mvpdroid.presenters;

import android.util.Log;

import com.robertmm.mvpdroid.entities.Album;
import com.robertmm.mvpdroid.models.AlbumListModel;
import com.robertmm.mvpdroid.models.AlbumListModelImp;
import com.robertmm.mvpdroid.ui.views.AlbumListView;
import com.robertmm.mvpdroid.utils.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roberto on 1/26/16.
 */
public class AlbumListPresenterImpl implements AlbumListPresenter {
    private static final String TAG = "AlbumListPresenterImpl";
    private final AlbumListModel model;

    private AlbumListView view;
    private List<Album> albums;

    public AlbumListPresenterImpl(AlbumListView view) {
        this.view = view;
        model = new AlbumListModelImp();
    }

    @Override
    public void create() {
        if (albums == null) {
            albums = new ArrayList<>();
            model.getAllAlbums(new Callback<List<Album>>() {
                @Override
                public void success(List<Album> element) {
                    albums.addAll(element);
                    view.setAlbums(albums);
                }

                @Override
                public void failure(String string) {
                    Log.e(TAG, "failure: " + string);

                }
            });
        } else {
            view.setAlbums(albums);
        }
    }

    @Override
    public void setView(AlbumListView view) {
        this.view=view;
    }
}