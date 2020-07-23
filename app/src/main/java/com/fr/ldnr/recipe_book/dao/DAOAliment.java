package com.fr.ldnr.recipe_book.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.fr.ldnr.recipe_book.model.AlimentObject;
import com.fr.ldnr.recipe_book.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class DAOAliment {

    private DBHelper db;
    private SQLiteDatabase database;

    public DAOAliment(Context context) {
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

    // fonction insertion d'un aliment
    public boolean insertAliment(String aliment_name) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.ALIMENT_NAME, aliment_name);

        if (database.insert(DBHelper.TABLE_ALIMENT, null, contentValues) == -1) {
            return false;
        } else return true;
    }

    //fonction suppression d'un aliment
    public boolean deleteAliment(String nom) {
        if (database.delete(DBHelper.TABLE_ALIMENT, DBHelper.ALIMENT_NAME + "=?", new String[]{nom}) == 0) {
            return false;
        } else return true;
    }

    //fonction pour récupérer un aliment
    public Cursor getAliment(String nom) {
        return database.rawQuery("select * from " + DBHelper.TABLE_ALIMENT + " where " + DBHelper.ALIMENT_NAME + " = " + "'" + nom + "'" + "", null);
    }


    // fonction pour récupérer tous les aliments
    public List<AlimentObject> getAllAliments() {

        List<AlimentObject> cities = new ArrayList<>();

        Cursor result = database.rawQuery("select * from " + DBHelper.TABLE_ALIMENT, null);

        while (result.moveToNext()) {
            cities.add(new AlimentObject(result.getInt(1),
                    result.getString(2)));
        }

        result.close();
        return cities;
    }

    //fonction modification d'un aliment
    public boolean updateAliment(int aliment_id, String aliment_name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.ALIMENT_ID, aliment_id);
        contentValues.put(DBHelper.ALIMENT_NAME, aliment_name);

        if (database.update(DBHelper.TABLE_ALIMENT, contentValues, DBHelper.ALIMENT_NAME + "=?", new String[]{aliment_name}) > 0) {
            return true;
        } else return false;
    }

    public int numbersOfRows() {
        openLect();
        return (int) DatabaseUtils.queryNumEntries(database, DBHelper.TABLE_ALIMENT);
    }

    public boolean populateAliment() {
        boolean complete = true;
        numbersOfRows();
        if (numbersOfRows() == 0) {
            boolean isInserted1 = insertAliment("");
            if (!isInserted1) complete = false;
            boolean isInserted2 = insertAliment("comté");
            if (!isInserted2) complete = false;
            boolean isInserted3 = insertAliment("melon");
            if (!isInserted3) complete = false;
            boolean isInserted4 = insertAliment("jambon cru");
            if (!isInserted4) complete = false;
            boolean isInserted5 = insertAliment("courgettes");
            if (!isInserted5) complete = false;
            boolean isInserted6 = insertAliment("chorizo");
            if (!isInserted6) complete = false;
        }
        return complete;
    }
}

