package com.fr.ldnr.recipe_book.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fr.ldnr.recipe_book.model.RecipeObject;
import com.fr.ldnr.recipe_book.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class DAORecipe {

    private static final String RECIPE_ID           = "recipe_id";
    private static final String RECIPE_TITLE        = "recipe_title";
    private static final String RECIPE_NOTE         = "recipe_note";
    private static final String RECIPE_CATEGORY     = "recipe_category";
    private static final String RECIPE_FILE         = "recipe_file";
    private static final String FK_RECIPE_ALIMENT_1 = "fk_aliment_1";
    private static final String FK_RECIPE_ALIMENT_2 = "fk_aliment_2";

    private DBHelper db;
    private SQLiteDatabase database;

    public DAORecipe(Context context) {
        db = new DBHelper((context));
    }

    public void open() throws SQLException {
        database = db.getWritableDatabase();
    }

    public void openLect() throws SQLException {
        database = db.getReadableDatabase();
    }

    public void close() {
        db.close();
    }

    // fonction insertion d'une capitale
    public boolean insertCapitale(int recipe_id, String recipe_title, String recipe_note, String recipe_category,
                                  String recipe_file, int fk_recipe_aliment_1, int fk_recipe_aliment_2) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(RECIPE_ID, recipe_id);
        contentValues.put(RECIPE_TITLE, recipe_title);
        contentValues.put(RECIPE_NOTE, recipe_note);
        contentValues.put(RECIPE_CATEGORY, recipe_category);
        contentValues.put(RECIPE_FILE, recipe_file);
        contentValues.put(FK_RECIPE_ALIMENT_1, fk_recipe_aliment_1);
        contentValues.put(FK_RECIPE_ALIMENT_2, fk_recipe_aliment_2);

        if (database.insert(TABLE_NAME, null, contentValues) == -1) {
            return false;
        } else return true;
    }

    //fonction suppression d'une capitale
    public boolean deleteData(String nom) {
        if (database.delete(TABLE_NAME, COLUMN_CAPITAL + "=?", new String[]{nom}) == 0) {
            return false;
        } else return true;
    }
    //fonction pour récupérer une capitale
    public Cursor getCapital(String nom) {
        return database.rawQuery("select * from " + TABLE_NAME + " where " + COLUMN_CAPITAL + " = " + "'" + nom + "'" +"", null);
    }


    // fonction pour récupérer toutes les capitales
    public List<MyObject> getAllData() {

        List<MyObject> cities = new ArrayList<>();

        Cursor result = database.rawQuery("select * from " + TABLE_NAME, null);

        while (result.moveToNext()) {
            cities.add(new MyObject(result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)));
        }

        result.close();
        return cities;
    }

    //fonction modification d'une capitale
    public boolean updateCapital (String nameCAP, String namePays, String population, String adresse) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CAPITAL, nameCAP);
        contentValues.put(COLUMN_PAYS, namePays);
        contentValues.put(COLUMN_POP, population);
        contentValues.put(COLUMN_URL, adresse);

        if (database.update(TABLE_NAME, contentValues, COLUMN_CAPITAL + "=?", new String[] {nameCAP}) > 0) {
            return true;
        } else return false;
    }
}
