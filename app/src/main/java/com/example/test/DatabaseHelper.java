package com.example.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "app3.db";
    private static final int SCHEMA = 1;
    static final String TABLE = "cities";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CITY = "city";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS cities (" + COLUMN_ID
                + " INTEGER PRIMARY KEY," + COLUMN_CITY
                + " TEXT );");
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}
