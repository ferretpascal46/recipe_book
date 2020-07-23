package com.fr.ldnr.recipe_book.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    //Database name
    public static final String DATABASE_NAME = "recipeBook.db";

    //Database version
    public static final int DATABASE_VERSION = 1;

    //Database table
    public static final String TABLE_RECIPE = "recipe";
    public static final String TABLE_ALIMENT = "aliment";

    //RECIPE Table      - column names
    private static final String RECIPE_ID           = "recipe_id";
    private static final String RECIPE_TITLE        = "recipe_title";
    private static final String RECIPE_NOTE         = "recipe_note";
    private static final String RECIPE_CATEGORY     = "recipe_category";
    private static final String RECIPE_FILE         = "recipe_file";
    private static final String FK_RECIPE_ALIMENT_1 = "fk_aliment_1";
    private static final String FK_RECIPE_ALIMENT_2 = "fk_aliment_2";

    //ALMENT Table      - column names
    private static final String ALIMENT_ID      = "aliment_id";
    private static final String ALIMENT_NAME    = "aliment_name";

    //Table create statement
    //RECIPE Table      - create statement
    private static final String CREATE_TABLE_RECIPE =
            "CREATE TABLE "
            + TABLE_RECIPE + "("
            + RECIPE_ID             + " INTEGER PRIMARY KEY,"
            + RECIPE_TITLE          + " TEXT,"
            + RECIPE_NOTE           + " TEXT,"
            + RECIPE_CATEGORY       + " TEXT,"
            + RECIPE_FILE           + " TEXT,"
            + FK_RECIPE_ALIMENT_1   + " INTEGER,"
            + FK_RECIPE_ALIMENT_2   + " INTEGER,"
            +")";

    //Table create statement
    //ALIMENT Table      - create statement
    private static final String CREATE_TABLE_ALIMENT =
            "CREATE TABLE "
            + TABLE_ALIMENT + "("
            + ALIMENT_ID    + " INTEGER PRIMARY KEY,"
            + ALIMENT_NAME  + " TEXT,"
            +")";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    // création de la table
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RECIPE);
        db.execSQL(CREATE_TABLE_ALIMENT);
        Log.i("DATABASE", "onCreate invoked");
    }

    @Override
    // méthode minimale si montée de version
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALIMENT);
        onCreate(db);
    }
}

