package com.fr.ldnr.recipe_book.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Capital.db";
    public static final int DATABASE_VERSION = 4;
    public static final String TABLE_NAME = "CAPT";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_CAPITAL = "NAMECAP";
    public static final String COLUMN_PAYS = "NAMEPAYS";
    public static final String COLUMN_POP = "POPULATION";
    public static final String COLUMN_URL = "ADRESSE";

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    // création de la table
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE table " + TABLE_NAME +
                        " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAMECAP TEXT, NAMEPAYS TEXT, POPULATION TEXT, ADRESSE TEXT) ");
        Log.i("DATABASE", "onCreate invoked");
    }

    @Override
    // méthode minimale si montée de version
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

