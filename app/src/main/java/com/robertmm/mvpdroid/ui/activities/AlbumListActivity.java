package com.robertmm.mvpdroid.ui.activities;

import android.os.Bundle;

import com.robertmm.mvpdroid.R;
import com.robertmm.mvpdroid.entities.Album;
import com.robertmm.mvpdroid.presenters.AlbumListPresenter;
import com.robertmm.mvpdroid.presenters.AlbumListPresenterImpl;
import com.robertmm.mvpdroid.presenters.PresenterHolder;
import com.robertmm.mvpdroid.ui.adapters.AlbumRecyclerAdapter;
import com.robertmm.mvpdroid.ui.views.AlbumListView;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by roberto on 1/26/16.
 */
public class AlbumListActivity extends BaseActivity implements AlbumListView {

    RecyclerView recyclerView;
    private static final String TAG = "AlbumListActivity";

    private AlbumRecyclerAdapter adapter;
    private AlbumListPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        presenter = createPresenter();
        presenter.create();
    }

    public AlbumListPresenter createPresenter() {
        AlbumListPresenter presenter = PresenterHolder.getInstance().
                getPresenter(AlbumListPresenter.class);
        if (presenter != null) {
            presenter.setView(this);
        } else {
            presenter = new AlbumListPresenterImpl(this);
        }
        return presenter;
    }

    @Override
    Integer getLayout() {
        return R.layout.album_list;
    }


    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        presenter.setView(null);
        PresenterHolder.getInstance().putPresenter(AlbumListPresenter.class, presenter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.isFinishing()) {
            PresenterHolder.getInstance().remove(AlbumListPresenter.class);
        }
    }


    @Override
    public void setAlbums(List<Album> albums) {
        adapter = new AlbumRecyclerAdapter(albums);
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "setAlbums: Adapter set");
    }
}
