package com.robertmm.mvpdroid.utils.images;

import com.squareup.picasso.Transformation;
import android.widget.ImageView;

/**
 * Created by roberto on 1/26/16.
 */
public interface ImageLoader {
    void load(ImageView view, String url);

    void loadWithTransformation(ImageView view, String url, Transformation transformation);
}