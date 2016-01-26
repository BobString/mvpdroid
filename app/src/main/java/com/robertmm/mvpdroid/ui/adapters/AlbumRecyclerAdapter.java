package com.robertmm.mvpdroid.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robertmm.mvpdroid.R;
import com.robertmm.mvpdroid.entities.Album;

import java.util.List;

/**
 * Created by roberto on 1/26/16.
 */
public class AlbumRecyclerAdapter extends RecyclerView.Adapter<AlbumRecyclerAdapter.AlbumViewHolder> {
    private List<Album> albums;

    public AlbumRecyclerAdapter(List<Album> albumList) {
        if (albumList == null) {
            throw new NullPointerException("No null list");
        }
        albums = albumList;
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
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public Album getItem(int position) {
        return albums.get(position);
    }

    final static class AlbumViewHolder extends RecyclerView.ViewHolder {
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
