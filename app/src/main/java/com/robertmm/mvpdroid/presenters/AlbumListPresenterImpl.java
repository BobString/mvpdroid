package com.robertmm.mvpdroid.presenters;

import android.util.Log;

import com.robertmm.mvpdroid.ApplicationClass;
import com.robertmm.mvpdroid.entities.Album;
import com.robertmm.mvpdroid.models.AlbumListModel;
import com.robertmm.mvpdroid.models.AlbumListModelImpl;
import com.robertmm.mvpdroid.ui.activities.AlbumListActivity;
import com.robertmm.mvpdroid.ui.views.AlbumListView;
import com.robertmm.mvpdroid.utils.Callback;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class AlbumListPresenterImpl implements AlbumListPresenter {
    private static final String TAG = "AlbumListPresenterImpl";

    AlbumListModelImpl model;
    AlbumListView view;

    private List<Album> albums;

    @Inject
    public AlbumListPresenterImpl(AlbumListActivity view, AlbumListModelImpl model) {
        this.view = view;
        this.model = model;
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
