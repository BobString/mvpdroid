package com.robertmm.mvpdroid.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.robertmm.mvpdroid.ApplicationClass;
import com.robertmm.mvpdroid.R;
import com.robertmm.mvpdroid.entities.Photo;
import com.robertmm.mvpdroid.injection.components.DaggerAlbumListComponent;
import com.robertmm.mvpdroid.injection.components.DaggerPhotoListComponent;
import com.robertmm.mvpdroid.injection.modules.AlbumListModule;
import com.robertmm.mvpdroid.injection.modules.PhotoListModule;
import com.robertmm.mvpdroid.presenters.AlbumListPresenter;
import com.robertmm.mvpdroid.presenters.PhotoListPresenter;
import com.robertmm.mvpdroid.presenters.PhotoListPresenterImpl;
import com.robertmm.mvpdroid.presenters.PresenterHolder;
import com.robertmm.mvpdroid.ui.adapters.PhotoRecyclerAdapter;
import com.robertmm.mvpdroid.ui.views.PhotoListView;

import java.util.List;

import javax.inject.Inject;


public class PhotoListActivity extends BaseActivity implements PhotoListView {

    RecyclerView recyclerView;
    private int albumId;
    public final static String EXTRA_ALBUMID = "albumId";
    public static final String TRANSITION_SHARED_ELEMENT = "content";
    private static final String TAG = "PhotoListActivity";

    private PhotoRecyclerAdapter adapter;

    @Inject
    PhotoListPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        albumId = extras.getInt(EXTRA_ALBUMID);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DaggerPhotoListComponent.builder()
                .databaseComponent(((ApplicationClass) getApplication()).getDatabaseComponent())
                .photoListModule(new PhotoListModule(this, albumId))
                .build().inject(this);

        presenter.create();
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
    public void setPhotos(List<Photo> photos) {
        adapter = new PhotoRecyclerAdapter(photos, this);
        recyclerView.setAdapter(adapter);
    }
}
