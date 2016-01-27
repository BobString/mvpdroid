package com.robertmm.mvpdroid.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.robertmm.mvpdroid.R;
import com.robertmm.mvpdroid.entities.Album;
import com.robertmm.mvpdroid.entities.Photo;
import com.robertmm.mvpdroid.ui.activities.AlbumListActivity;
import com.robertmm.mvpdroid.ui.activities.PhotoActivity;
import com.robertmm.mvpdroid.ui.activities.PhotoListActivity;
import com.robertmm.mvpdroid.utils.images.ImageLoader;
import com.robertmm.mvpdroid.utils.images.PicassoImageLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by roberto on 1/26/16.
 */
public class PhotoRecyclerAdapter extends RecyclerView.Adapter<PhotoRecyclerAdapter.PhotoViewHolder> {
    private List<Photo> photos;
    private Context context;
    private static final String TAG = "PhotoRecyclerAdapter";

    public PhotoRecyclerAdapter(List<Photo> photoList, Context context) {
        if (photoList == null) {
            throw new NullPointerException("No null list");
        }
        this.photos = photoList;
        this.context = context;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_cell, parent, false);
        return new PhotoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, final int position) {
        final Photo photo = photos.get(position);
        ImageLoader loader = new PicassoImageLoader(context);
        loader.load(holder.thumbnail, photo.getThumbnailUrl().replace("http","https"));
        String id = ""+photo.getId();
        holder.id.setText(id);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Photo photo = photos.get(position);
                Intent startIntent = new Intent(context, PhotoActivity.class);
                startIntent.putExtra(PhotoActivity.EXTRA_PHOTO, photo);
                View innerContainer = v.findViewById(R.id.container_inner_item_cell);
                ActivityOptionsCompat options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((PhotoListActivity)context, innerContainer,
                                PhotoListActivity.TRANSITION_SHARED_ELEMENT);
                ActivityCompat.startActivity((PhotoListActivity) context, startIntent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public Photo getItem(int position) {
        return photos.get(position);
    }

    final static class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView id;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.photo_thumbnail);
            id = (TextView) itemView.findViewById(R.id.photo_id);

        }

    }

    public void addAll(List<Photo> photos){
        this.photos.addAll(photos);
        notifyDataSetChanged();
    }
}
