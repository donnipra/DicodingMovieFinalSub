package com.donnipra.favoriteapp.database.provider;

/*
 * Created by donni.
 * Last modified 6/12/18 7:18 PM.
 */

import android.provider.BaseColumns;

public class FavoriteColumns implements BaseColumns {
    public static String TABLE_NAME = "favorite_movie";

    public static String COLUMN_ID = "_id";
    public static String COLUMN_TITLE = "title";
    public static String COLUMN_POSTER = "poster";
    public static String COLUMN_RELEASE_DATE = "release_date";
    public static String COLUMN_AVERAGE = "vote_average";
    public static String COLUMN_COUNT = "vote_count";
    public static String COLUMN_POPULARITY = "popularity";
    public static String COLUMN_OVERVIEW = "overview";
}
