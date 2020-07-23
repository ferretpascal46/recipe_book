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
    public static final String TABLE_ASSOCIATION_REC_ALI = "association_rec_ali";

    //RECIPE Table                  - column names
    public static final String RECIPE_ID           = "recipe_id";
    public static final String RECIPE_TITLE        = "recipe_title";
    public static final String RECIPE_NOTE         = "recipe_note";
    public static final String RECIPE_CATEGORY     = "recipe_category";
    public static final String RECIPE_FILE         = "recipe_file";

    //ALIMENT Table                 - column names
    public static final String ALIMENT_ID      = "aliment_id";
    public static final String ALIMENT_NAME    = "aliment_name";

    //ASSOCIATION_REC_ALI Table     - column names
    public static final String FK_RECIPE      = "fk_recipe";
    public static final String FK_ALIMENT      = "fk_aliment";



    //Table create statement
    //RECIPE Table          - create statement
    private static final String CREATE_TABLE_RECIPE =
            "CREATE TABLE "
            + TABLE_RECIPE + "("
            + RECIPE_ID             + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + RECIPE_TITLE          + " TEXT,"
            + RECIPE_NOTE           + " TEXT,"
            + RECIPE_CATEGORY       + " TEXT,"
            + RECIPE_FILE           + " INTEGER"
            /*+ FK_RECIPE_ALIMENT_1   + " INTEGER"
            + FK_RECIPE_ALIMENT_2   + " INTEGER,"

            + " FOREIGN KEY ("+FK_RECIPE_ALIMENT_1+") REFERENCES "
                    +TABLE_ALIMENT+"("+ALIMENT_ID+"),"
            + " FOREIGN KEY ("+FK_RECIPE_ALIMENT_2+") REFERENCES "
                    +TABLE_ALIMENT+"("+ALIMENT_ID+")"*/
            +");";

    //ALIMENT Table         - create statement
    private static final String CREATE_TABLE_ALIMENT =
            "CREATE TABLE "
            + TABLE_ALIMENT + "("
            + ALIMENT_ID    + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ALIMENT_NAME  + " TEXT"
            +")";

    //ASSOCIATION_REC_ALI Table     - create statement
    private static final String CREATE_TABLE_ASSOCIATION_REC_ALI =
            "CREATE TABLE "
                    + TABLE_ASSOCIATION_REC_ALI + "("
                    + FK_RECIPE  + " INTEGER NOT NULL,"
                    + FK_ALIMENT + " INTEGER NOT NULL, "
                    + "PRIMARY KEY ("+FK_RECIPE+" , "+FK_ALIMENT+")"
                    +");";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    // création de la table
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RECIPE);
        db.execSQL(CREATE_TABLE_ALIMENT);
        db.execSQL(CREATE_TABLE_ASSOCIATION_REC_ALI);
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

