package com.robertmm.mvpdroid.presenters;

import com.robertmm.mvpdroid.ui.views.PhotoListView;

/**
 * Created by roberto on 1/26/16.
 */
public interface PhotoListPresenter extends Presenter {

    void create();
    void setView(PhotoListView view);
}
