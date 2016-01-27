package com.robertmm.mvpdroid.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robertmm.mvpdroid.R;
import com.robertmm.mvpdroid.entities.Album;
import com.robertmm.mvpdroid.ui.activities.AlbumListActivity;
import com.robertmm.mvpdroid.ui.activities.PhotoListActivity;

import java.util.List;

/**
 * Created by roberto on 1/26/16.
 */
public class AlbumRecyclerAdapter extends RecyclerView.Adapter<AlbumRecyclerAdapter.AlbumViewHolder> {
    private List<Album> albums;
    private Context context;
    private static final String TAG = "AlbumRecyclerAdapter";

    public AlbumRecyclerAdapter(List<Album> albumList, Context context) {
        if (albumList == null) {
            throw new NullPointerException("No null list");
        }
        this.albums = albumList;
        this.context = context;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_row, parent, false);
        return new AlbumViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        final Album album = albums.get(position);
        holder.title.setText(album.getTitle());
        holder.id.setText(""+album.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(context, PhotoListActivity.class);
                startIntent.putExtra(PhotoListActivity.EXTRA_ALBUMID, album.getId());
                View innerContainer = v.findViewById(R.id.album_inner_container);
                ActivityOptionsCompat options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((AlbumListActivity)context, innerContainer,
                                PhotoListActivity.TRANSITION_SHARED_ELEMENT);
                ActivityCompat.startActivity((AlbumListActivity) context, startIntent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public Album getItem(int position) {
        return albums.get(position);
    }

    final static class AlbumViewHolder extends RecyclerView.ViewHolder  {
        TextView title;
        TextView id;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.album_title);
            id = (TextView) itemView.findViewById(R.id.album_id);
        }

    }

    public void addAll(List<Album> albums){
        this.albums.addAll(albums);
        notifyDataSetChanged();
    }
}
