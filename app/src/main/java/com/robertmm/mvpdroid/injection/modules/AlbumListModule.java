package com.robertmm.mvpdroid.injection.modules;

import android.app.Application;

import com.robertmm.mvpdroid.ApplicationClass;
import com.robertmm.mvpdroid.injection.scopes.CustomScope;
import com.robertmm.mvpdroid.models.AlbumListModel;
import com.robertmm.mvpdroid.models.AlbumListModelImpl;
import com.robertmm.mvpdroid.presenters.AlbumListPresenterImpl;
import com.robertmm.mvpdroid.ui.activities.AlbumListActivity;
import com.robertmm.mvpdroid.ui.views.AlbumListView;

import dagger.Module;
import dagger.Provides;

@Module
public class AlbumListModule {

    private final AlbumListActivity mView;

    public AlbumListModule(AlbumListActivity mView) {
        this.mView = mView;
    }

    @Provides
    @CustomScope
    AlbumListActivity providesAlbumListActivity() {
        return mView;
    }

    @Provides
    @CustomScope
    AlbumListModel providesAlbumListModel(Application applicationClass) {
        return new AlbumListModelImpl(applicationClass);
    }

    @Provides
    @CustomScope
    AlbumListPresenterImpl providesAlbumListPresenter(AlbumListActivity view, AlbumListModelImpl model) {
        return new AlbumListPresenterImpl(view, model);
    }

}
