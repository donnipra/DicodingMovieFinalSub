package com.donnipra.dicodingsub4.ui.detail;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.donnipra.dicodingsub4.BuildConfig;
import com.donnipra.dicodingsub4.R;
import com.donnipra.dicodingsub4.api.APIClient;
import com.donnipra.dicodingsub4.database.provider.FavoriteColumns;
import com.donnipra.dicodingsub4.database.provider.FavoriteHelper;
import com.donnipra.dicodingsub4.model.DetailModel;
import com.donnipra.dicodingsub4.model.MovieItemModel;
import com.donnipra.dicodingsub4.util.DateTime;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.donnipra.dicodingsub4.database.DatabaseContract.CONTENT_URI;

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

    @BindView(R.id.btn_favorite)
    Button btn_favorite;

    private Call<DetailModel> apiCall;
    private APIClient apiClient = new APIClient();
    private Gson gson = new Gson();

    private MovieItemModel item;
    private FavoriteHelper favoriteHelper;
    private Boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        String json = getIntent().getStringExtra(MOVIE_ITEM);
        item = gson.fromJson(json, MovieItemModel.class);
        loadData();

        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFavorite) FavoriteRemove();
                else FavoriteSave();

                isFavorite = !isFavorite;
                favoriteSet();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (apiCall != null) apiCall.cancel();
        if (favoriteHelper != null) favoriteHelper.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void favoriteSet() {
        if (isFavorite) btn_favorite.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favorite_red_12dp,0,0,0);
        else btn_favorite.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favorite_border_12dp,0,0,0);
    }

    private void loadData() {
        loadDataSQLite();
        loadDataInServer(String.valueOf(item.getId()));

        tv_title.setText(item.getTitle());

        Glide.with(this)
                .load(BuildConfig.BASE_URL_IMG + "w154" + item.getPosterPath())
                .into(img_poster);

        tv_release_date.setText(DateTime.getLongDate(item.getReleaseDate()));
        tv_vote_average.setText(String.valueOf(item.getVoteAverage()));
        tv_vote_count.setText(" ( "+String.valueOf(item.getVoteCount())+" ) ");


    }

    private void loadDataSQLite() {
        favoriteHelper = new FavoriteHelper(this);
        favoriteHelper.open();

        Cursor cursor = getContentResolver().query(
                Uri.parse(CONTENT_URI + "/" + item.getId()),
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) isFavorite = true;
            cursor.close();
        }
        favoriteSet();
    }

    private void loadDataInServer(String movie_item) {
        apiCall = apiClient.getService().getDetailMovie(movie_item, "en");
        apiCall.enqueue(new Callback<DetailModel>() {
            @Override
            public void onResponse(Call<DetailModel> call, Response<DetailModel> response) {
                if (response.isSuccessful()) {
                    DetailModel item = response.body();

                    int size = 0;

                } else loadFailed();
            }

            @Override
            public void onFailure(Call<DetailModel> call, Throwable t) {
                loadFailed();
            }
        });
    }

    private void loadFailed() {
        Toast.makeText(this, R.string.err_load_failed, Toast.LENGTH_SHORT).show();
    }

    private void FavoriteSave() {
        //Log.d("TAG", "FavoriteSave: " + item.getId());
        ContentValues cv = new ContentValues();
        cv.put(FavoriteColumns.COLUMN_ID, item.getId());
        cv.put(FavoriteColumns.COLUMN_TITLE, item.getTitle());
        cv.put(FavoriteColumns.COLUMN_POSTER, item.getPosterPath());
        cv.put(FavoriteColumns.COLUMN_AVERAGE, item.getVoteAverage());
        cv.put(FavoriteColumns.COLUMN_RELEASE_DATE, item.getReleaseDate());
        cv.put(FavoriteColumns.COLUMN_COUNT, item.getVoteCount());
        cv.put(FavoriteColumns.COLUMN_OVERVIEW, item.getOverview());
        cv.put(FavoriteColumns.COLUMN_POPULARITY, item.getPopularity());

        getContentResolver().insert(CONTENT_URI, cv);
        Toast.makeText(this, R.string.cv_save, Toast.LENGTH_SHORT).show();
    }

    private void FavoriteRemove() {
        getContentResolver().delete(
                Uri.parse(CONTENT_URI + "/" + item.getId()),
                null,
                null
        );
        Toast.makeText(this, R.string.cv_remove, Toast.LENGTH_SHORT).show();
    }
}
