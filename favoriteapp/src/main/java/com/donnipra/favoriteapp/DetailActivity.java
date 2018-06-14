package com.donnipra.favoriteapp;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.donnipra.favoriteapp.model.FavoriteModel;
import com.donnipra.favoriteapp.util.DateTime;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String MOVIE_ITEM = "movie_item";

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.img_poster)
    ImageView img_poster;

    @BindView(R.id.tv_overview)
    TextView tv_overview;

    @BindView(R.id.tv_release_date)
    TextView tv_release_date;

    @BindView(R.id.tv_vote_average)
    TextView tv_vote_average;

    @BindView(R.id.tv_vote_count)
    TextView tv_vote_count;

    @BindView(R.id.tv_popularity)
    TextView tv_popularity;

    private FavoriteModel item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        loadData();
        storeData();
    }

    private void loadData() {
        Uri uri = getIntent().getData();
        if (uri == null) return;
        Cursor cursor = getContentResolver().query(
                uri,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) item = new FavoriteModel(cursor);
            cursor.close();
        }
    }

    private void storeData() {
        if (item == null) return;

        Glide.with(this)
                .load("http://image.tmdb.org/t/p/w342/" + item.getPosterPath())
                .into(img_poster);

        tv_title.setText(item.getTitle());
        tv_release_date.setText(DateTime.getLongDate(item.getReleaseDate()));
        tv_vote_average.setText(String.valueOf(item.getVoteAverage()));
        tv_vote_count.setText(" ( "+String.valueOf(item.getVoteCount())+" ) ");
        tv_popularity.setText(String.valueOf(item.getPopularity()));
        tv_overview.setText(item.getOverview());
    }
}
