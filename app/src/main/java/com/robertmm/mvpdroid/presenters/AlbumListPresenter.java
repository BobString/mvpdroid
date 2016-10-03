package com.robertmm.mvpdroid.presenters;

import com.robertmm.mvpdroid.ui.views.AlbumListView;


public interface AlbumListPresenter extends Presenter {

    void create();
    void setView(AlbumListView view);
}
