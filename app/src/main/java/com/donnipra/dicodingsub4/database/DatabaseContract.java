package com.donnipra.dicodingsub4.database;

/*
 * Created by donni.
 * Last modified 6/12/18 7:25 PM.
 */

import android.database.Cursor;
import android.net.Uri;

import com.donnipra.dicodingsub4.database.provider.FavoriteColumns;

public class DatabaseContract {
    public static final String AUTHORITY = "com.donnipra.moviedb";

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(FavoriteColumns.TABLE_NAME)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static double getColumnDouble(Cursor cursor, String columnName) {
        return cursor.getDouble(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }
}
