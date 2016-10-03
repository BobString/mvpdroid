package com.robertmm.mvpdroid.injection.modules;

import android.app.Application;

import com.robertmm.mvpdroid.injection.scopes.CustomScope;
import com.robertmm.mvpdroid.models.AlbumListModelImpl;
import com.robertmm.mvpdroid.models.PhotoListModel;
import com.robertmm.mvpdroid.models.PhotoListModelImpl;
import com.robertmm.mvpdroid.presenters.AlbumListPresenterImpl;
import com.robertmm.mvpdroid.presenters.PhotoListPresenterImpl;
import com.robertmm.mvpdroid.ui.activities.AlbumListActivity;
import com.robertmm.mvpdroid.ui.activities.PhotoListActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class PhotoListModule {

    private final PhotoListActivity mView;
    private int albumId;

    public PhotoListModule(PhotoListActivity mView, int albumId) {
        this.mView = mView;
        this.albumId = albumId;
    }

    @Provides
    @CustomScope
    PhotoListActivity providesPhotoListActivity() {
        return mView;
    }

    @Provides
    @CustomScope
    PhotoListModel providesPhotoListModel(Application application) {
        return new PhotoListModelImpl(application, albumId);
    }

    @Provides
    @CustomScope
    PhotoListPresenterImpl providesPhotoListPresenter(PhotoListActivity view, PhotoListModel model) {
        return new PhotoListPresenterImpl(view, model);
    }

}
