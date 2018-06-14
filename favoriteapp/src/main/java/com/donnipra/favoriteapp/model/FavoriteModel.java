package com.donnipra.favoriteapp.model;

/*
 * Created by donni.
 * Last modified 6/12/18 9:03 PM.
 */

import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import static android.provider.BaseColumns._ID;
import static com.donnipra.favoriteapp.database.DatabaseContract.getColumnDouble;
import static com.donnipra.favoriteapp.database.DatabaseContract.getColumnInt;
import static com.donnipra.favoriteapp.database.DatabaseContract.getColumnString;
import static com.donnipra.favoriteapp.database.provider.FavoriteColumns.COLUMN_AVERAGE;
import static com.donnipra.favoriteapp.database.provider.FavoriteColumns.COLUMN_COUNT;
import static com.donnipra.favoriteapp.database.provider.FavoriteColumns.COLUMN_OVERVIEW;
import static com.donnipra.favoriteapp.database.provider.FavoriteColumns.COLUMN_POPULARITY;
import static com.donnipra.favoriteapp.database.provider.FavoriteColumns.COLUMN_POSTER;
import static com.donnipra.favoriteapp.database.provider.FavoriteColumns.COLUMN_RELEASE_DATE;
import static com.donnipra.favoriteapp.database.provider.FavoriteColumns.COLUMN_TITLE;

public class FavoriteModel {
        @SerializedName("id")
        private int id;

        @SerializedName("title")
        private String title;

        @SerializedName("vote_count")
        private int voteCount;

        @SerializedName("poster_path")
        private String posterPath;

        @SerializedName("release_date")
        private String releaseDate;

        @SerializedName("vote_average")
        private double voteAverage;

        @SerializedName("overview")
        private String overview;

        @SerializedName("popularity")
        private double popularity;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setVoteCount(int voteCount) {
            this.voteCount = voteCount;
        }

        public int getVoteCount() {
            return voteCount;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setVoteAverage(double voteAverage) {
            this.voteAverage = voteAverage;
        }

        public double getVoteAverage() {
            return voteAverage;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getOverview() {
            return overview;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public double getPopularity() {
            return popularity;
        }

        public FavoriteModel() {
        }

        public FavoriteModel(Cursor cursor) {
            this.id = getColumnInt(cursor, _ID);
            this.title = getColumnString(cursor, COLUMN_TITLE);
            this.overview = getColumnString(cursor, COLUMN_OVERVIEW);
            this.posterPath = getColumnString(cursor, COLUMN_POSTER);
            this.releaseDate = getColumnString(cursor, COLUMN_RELEASE_DATE);
            this.voteAverage = getColumnDouble(cursor, COLUMN_AVERAGE);
            this.voteCount = getColumnInt(cursor, COLUMN_COUNT);
            this.popularity = getColumnDouble(cursor, COLUMN_POPULARITY);
        }
}
