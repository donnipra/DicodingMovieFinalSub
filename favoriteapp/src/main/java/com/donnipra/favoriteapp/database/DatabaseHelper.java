package com.donnipra.favoriteapp.database;

/*
 * Created by donni.
 * Last modified 6/12/18 7:14 PM.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.donnipra.favoriteapp.database.provider.FavoriteColumns;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "movie";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_MOVIE = "create table " + FavoriteColumns.TABLE_NAME + " (" +
                FavoriteColumns.COLUMN_ID + " integer primary key autoincrement, " +
                FavoriteColumns.COLUMN_TITLE + " text not null, " +
                FavoriteColumns.COLUMN_POSTER + " text not null, " +
                FavoriteColumns.COLUMN_RELEASE_DATE + " text not null, " +
                FavoriteColumns.COLUMN_AVERAGE + " text not null, " +
                FavoriteColumns.COLUMN_COUNT + " text not null, " +
                FavoriteColumns.COLUMN_POPULARITY + " text not null, " +
                FavoriteColumns.COLUMN_OVERVIEW + " text not null " +
                ");";

        sqLiteDatabase.execSQL(CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FavoriteColumns.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
