package com.robertmm.mvpdroid.presenters;

import com.robertmm.mvpdroid.ui.views.AlbumListView;

/**
 * Created by roberto on 1/26/16.
 */
public interface AlbumListPresenter extends Presenter {

    void create();
    void setView(AlbumListView view);
}
