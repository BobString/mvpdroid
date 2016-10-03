package com.robertmm.mvpdroid.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.robertmm.mvpdroid.ApplicationClass;
import com.robertmm.mvpdroid.R;
import com.robertmm.mvpdroid.entities.Album;
import com.robertmm.mvpdroid.injection.components.DaggerAlbumListComponent;
import com.robertmm.mvpdroid.injection.modules.AlbumListModule;
import com.robertmm.mvpdroid.presenters.AlbumListPresenterImpl;
import com.robertmm.mvpdroid.ui.adapters.AlbumRecyclerAdapter;
import com.robertmm.mvpdroid.ui.views.AlbumListView;

import java.util.List;

import javax.inject.Inject;


public class AlbumListActivity extends BaseActivity implements AlbumListView {

    RecyclerView recyclerView;
    private static final String TAG = "AlbumListActivity";

    private AlbumRecyclerAdapter adapter;

    @Inject
    AlbumListPresenterImpl presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerAlbumListComponent.builder()
                .databaseComponent(((ApplicationClass) getApplication()).getDatabaseComponent())
                .albumListModule(new AlbumListModule(this))
                .build().inject(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        presenter.create();
    }


    @Override
    Integer getLayout() {
        return R.layout.album_list;
    }


    @Override
    protected void onSaveInstanceState(Bundle bundle) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void setAlbums(List<Album> albums) {
        adapter = new AlbumRecyclerAdapter(albums, this);
        recyclerView.setAdapter(adapter);
    }
}
