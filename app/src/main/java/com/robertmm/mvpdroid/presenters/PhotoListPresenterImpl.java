package com.robertmm.mvpdroid.presenters;

import android.util.Log;

import com.robertmm.mvpdroid.entities.Album;
import com.robertmm.mvpdroid.entities.Photo;
import com.robertmm.mvpdroid.models.PhotoListModel;
import com.robertmm.mvpdroid.models.PhotoListModelImpl;
import com.robertmm.mvpdroid.ui.views.AlbumListView;
import com.robertmm.mvpdroid.ui.views.PhotoListView;
import com.robertmm.mvpdroid.utils.Callback;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class PhotoListPresenterImpl implements PhotoListPresenter {
    private static final String TAG = "PhotoListPresenterImpl";
     PhotoListModel model;

    private PhotoListView view;
    private List<Photo> photos;

    @Inject
    public PhotoListPresenterImpl(PhotoListView view, PhotoListModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void create() {
        if (photos == null) {
            photos = new ArrayList<>();
            model.getAllPhotosFromAlbum(new Callback<List<Photo>>() {
                @Override
                public void success(List<Photo> element) {
                    photos.addAll(element);
                    view.setPhotos(photos);
                }

                @Override
                public void failure(String string) {
                    Log.e(TAG, "failure: " + string);

                }
            });
        } else {
            view.setPhotos(photos);
        }
    }

    @Override
    public void setView(PhotoListView view) {
        this.view=view;
    }
}
