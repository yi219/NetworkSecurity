package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "diary.db";
    private static final int DATABASE_VERSION = 5;

    public static final String TABLE_NAME = "diary";
    public static final String DIARY_DATE = "date";
    public static final String DIARY_RATING = "rating";
    public static final String DIARY_TITLE = "title";
    public static final String DIARY_CONTENT = "content";
    String qry = "";

    public static final String[] ALL_COLUMNS = {DIARY_DATE, DIARY_RATING, DIARY_TITLE, DIARY_CONTENT};

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    //DIARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DIARY_DATE + " TEXT UNIQUE, " +
                    DIARY_TITLE + " TEXT, " +
                    DIARY_CONTENT + " TEXT, " +
                    DIARY_RATING + " REAL" +
                    ");";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(CREATE_TABLE);
        /*qry = "INSERT INTO diary(title) VALUES('2022-05-17')";
        sqLiteDatabase.execSQL(qry);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
