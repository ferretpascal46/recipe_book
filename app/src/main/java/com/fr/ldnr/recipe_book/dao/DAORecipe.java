package com.fr.ldnr.recipe_book.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fr.ldnr.recipe_book.model.RecipeObject;
import com.fr.ldnr.recipe_book.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class DAORecipe {

    private static final String DATABASE_NAME = "recipeBook.db";
    public static final String TABLE_RECIPE = "recipe";
    private static final String RECIPE_ID = "recipe_id";
    private static final String RECIPE_TITLE = "recipe_title";
    private static final String RECIPE_NOTE = "recipe_note";
    private static final String RECIPE_CATEGORY = "recipe_category";
    private static final String RECIPE_FILE = "recipe_file";
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

    // fonction insertion d'une recette
    public boolean insertRecipe(int recipe_id, String recipe_title, String recipe_note, String recipe_category,
                                String recipe_file, int fk_recipe_aliment_1, int fk_recipe_aliment_2) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(RECIPE_ID, recipe_id);
        contentValues.put(RECIPE_TITLE, recipe_title);
        contentValues.put(RECIPE_NOTE, recipe_note);
        contentValues.put(RECIPE_CATEGORY, recipe_category);
        contentValues.put(RECIPE_FILE, recipe_file);
        contentValues.put(FK_RECIPE_ALIMENT_1, fk_recipe_aliment_1);
        contentValues.put(FK_RECIPE_ALIMENT_2, fk_recipe_aliment_2);

        if (database.insert(TABLE_RECIPE, null, contentValues) == -1) {
            return false;
        } else return true;
    }

    //fonction suppression d'une recette
    public boolean deleteRecipe(String nom) {
        if (database.delete(TABLE_RECIPE, RECIPE_TITLE + "=?", new String[]{nom}) == 0) {
            return false;
        } else return true;
    }

    //fonction pour récupérer une recette
    public Cursor getRecipe(String nom) {
        return database.rawQuery("select * from " + TABLE_RECIPE + " where " + RECIPE_TITLE + " = " + "'" + nom + "'" + "", null);
    }


    // fonction pour récupérer toutes les recettes
    public List<RecipeObject> getAllRecipe() {

        List<RecipeObject> cities = new ArrayList<>();

        Cursor result = database.rawQuery("select * from " + TABLE_RECIPE, null);

        while (result.moveToNext()) {
            cities.add(new RecipeObject(result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getInt(6),
                    result.getInt(7)));
        }

        result.close();
        return cities;
    }

    //fonction modification d'une recette
    public boolean updateRecipe(int recipe_id, String recipe_title, String recipe_note, String recipe_category,
                                String recipe_file, int fk_recipe_aliment_1, int fk_recipe_aliment_2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(RECIPE_ID, recipe_id);
        contentValues.put(RECIPE_TITLE, recipe_title);
        contentValues.put(RECIPE_NOTE, recipe_note);
        contentValues.put(RECIPE_CATEGORY, recipe_category);
        contentValues.put(RECIPE_FILE, recipe_file);
        contentValues.put(FK_RECIPE_ALIMENT_1, fk_recipe_aliment_1);
        contentValues.put(FK_RECIPE_ALIMENT_2, fk_recipe_aliment_2);


        if (database.update(TABLE_RECIPE, contentValues, RECIPE_TITLE + "=?", new String[]{recipe_title}) > 0) {
            return true;
        } else return false;
    }

    public int numbersOfRows() {
        openLect();
        return (int) DatabaseUtils.queryNumEntries(database, TABLE_RECIPE);
    }

    public boolean populateRecipe() {
        boolean complete = true;
        numbersOfRows();
        if (numbersOfRows() == 0) {
            boolean isInserted0 = insertRecipe(0, "Gougère au comté", "", "Apéritif", "", 1, 0);
            if (!isInserted0) complete = false;
            boolean isInserted1 = insertRecipe(1, "Granité de melon et chips de jambon cru", "", "Apéritif", "", 2, 3);
            if (!isInserted1) complete = false;
            boolean isInserted2 = insertRecipe(2, "Madeleine courgettes chorizo", "", "Apéritif", "", 4, 5);
            if (!isInserted2) complete = false;
        }
        return complete;
    }
}

