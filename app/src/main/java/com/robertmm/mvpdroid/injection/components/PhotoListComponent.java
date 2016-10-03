package com.robertmm.mvpdroid.injection.components;

import com.robertmm.mvpdroid.injection.modules.PhotoListModule;
import com.robertmm.mvpdroid.injection.scopes.CustomScope;
import com.robertmm.mvpdroid.models.PhotoListModel;
import com.robertmm.mvpdroid.presenters.PhotoListPresenterImpl;
import com.robertmm.mvpdroid.ui.activities.PhotoListActivity;

import dagger.Component;

@CustomScope
@Component(dependencies = DatabaseComponent.class, modules = PhotoListModule.class)
public interface PhotoListComponent {

    void inject(PhotoListActivity activity);

    PhotoListModel photoListModel();

    PhotoListPresenterImpl photoListPresenter();
}
