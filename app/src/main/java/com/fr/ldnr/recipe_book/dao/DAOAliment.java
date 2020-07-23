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

    /**
     * insertion d'un aliment
     * @param aliment_name
     * @return
     */
    public boolean insertAliment(String aliment_name) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.ALIMENT_NAME, aliment_name);

        if (database.insert(DBHelper.TABLE_ALIMENT, null, contentValues) == -1) {
            return false;
        } else return true;
    }

    /**
     * suppression d'un aliment
     * @param nom
     * @return
     */
    public boolean deleteAliment(String nom) {
        if (database.delete(DBHelper.TABLE_ALIMENT, DBHelper.ALIMENT_NAME + "=?", new String[]{nom}) == 0) {
            return false;
        } else return true;
    }

    /**
     * récupérer un aliment
     * @param nom
     * @return
     */
    public Cursor getAliment(String nom) {
        return database.rawQuery("select * from " + DBHelper.TABLE_ALIMENT + " where " + DBHelper.ALIMENT_NAME + " = " + "'" + nom + "'" + "", null);
    }


    /**
     * récupérer tous les aliments
     * @return
     */
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

    /**
     * modification d'un aliment
     * @param aliment_id
     * @param aliment_name
     * @return
     */
    public boolean updateAliment(int aliment_id, String aliment_name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.ALIMENT_ID, aliment_id);
        contentValues.put(DBHelper.ALIMENT_NAME, aliment_name);

        if (database.update(DBHelper.TABLE_ALIMENT, contentValues, DBHelper.ALIMENT_NAME + "=?", new String[]{aliment_name}) > 0) {
            return true;
        } else return false;
    }

    /**
     * récupérer le nombre d'occurence de la table
     * @return
     */
    public int numbersOfRows() {
        openLect();
        return (int) DatabaseUtils.queryNumEntries(database, DBHelper.TABLE_ALIMENT);
    }

    /**
     * inserer quelques aliment dans la base de donnée
     * @return
     */
    public boolean populateAliment() {
        boolean complete = true;
        numbersOfRows();
        if (numbersOfRows() == 0) {
            boolean isInserted1 = insertAliment("");
            if (!isInserted1) complete = false;
            boolean isInserted2 = insertAliment("COMTÉ");
            if (!isInserted2) complete = false;
            boolean isInserted3 = insertAliment("MELON");
            if (!isInserted3) complete = false;
            boolean isInserted4 = insertAliment("JAMBON CRU");
            if (!isInserted4) complete = false;
            boolean isInserted5 = insertAliment("COURGETTES");
            if (!isInserted5) complete = false;
            boolean isInserted6 = insertAliment("CHORIZO");
            if (!isInserted6) complete = false;
        }
        return complete;
    }
}

