package com.robertmm.mvpdroid.ui.activities;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.robertmm.mvpdroid.R;
import com.robertmm.mvpdroid.entities.Photo;
import com.robertmm.mvpdroid.utils.images.ImageLoader;
import com.robertmm.mvpdroid.utils.images.PicassoImageLoader;

/**
 * Created by roberto on 1/26/16.
 */
public class PhotoActivity extends AppCompatActivity {
    public static final String EXTRA_PHOTO = "photo";
    public static final String TRANSITION_SHARED_ELEMENT = "content";

    private CardView cardView;
    private TextView titleView;
    private TextView id;
    private ImageView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        Photo photo = extras.getParcelable(EXTRA_PHOTO);

        setContentView(R.layout.photo_activity);
        cardView = (CardView) findViewById(R.id.cv);
        titleView = (TextView) findViewById(R.id.photo_title_act);
        id = (TextView) findViewById(R.id.photo_id_act);
        photoView = (ImageView) findViewById(R.id.photo_act);

        View innerContainer = cardView.findViewById(R.id.container_inner_item);
        ViewCompat.setTransitionName(innerContainer, TRANSITION_SHARED_ELEMENT);
        if (photo != null) {
            ImageLoader loader = new PicassoImageLoader(this);
            loader.load(photoView, photo.getUrl().replace("http", "https"));
            titleView.setText(photo.getTitle());
            id.setText(""+photo.getId());
        } else {
            finish();
        }
    }

}
