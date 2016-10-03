package com.robertmm.mvpdroid.injection.components;

import com.robertmm.mvpdroid.injection.modules.AlbumListModule;
import com.robertmm.mvpdroid.injection.scopes.CustomScope;
import com.robertmm.mvpdroid.models.AlbumListModel;
import com.robertmm.mvpdroid.presenters.AlbumListPresenterImpl;
import com.robertmm.mvpdroid.ui.activities.AlbumListActivity;

import dagger.Component;

@CustomScope
@Component(dependencies = DatabaseComponent.class, modules = AlbumListModule.class)
public interface AlbumListComponent {

    void inject(AlbumListActivity activity);

    AlbumListModel albumListModel();

    AlbumListPresenterImpl albumListPresenter();
}
