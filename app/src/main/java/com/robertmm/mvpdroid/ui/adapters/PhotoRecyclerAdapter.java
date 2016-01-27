package com.robertmm.mvpdroid.ui.adapters;

import android.content.Context;
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
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        final Photo photo = photos.get(position);
        ImageLoader loader = new PicassoImageLoader(context);
        loader.load(holder.thumbnail, photo.getThumbnailUrl().replace("http","https"));
        String id = ""+photo.getId();
        holder.id.setText(id);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public Photo getItem(int position) {
        return photos.get(position);
    }

    final static class PhotoViewHolder extends RecyclerView.ViewHolder {
        TextView title;
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
