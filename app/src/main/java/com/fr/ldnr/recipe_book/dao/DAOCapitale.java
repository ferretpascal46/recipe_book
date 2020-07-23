package com.fr.ldnr.recipe_book.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.fr.ldnr.recipe_book.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class DAOCapitale {

    public static final String TABLE_NAME = "CAPT";
    public static final String COLUMN_CAPITAL = "NAMECAP";
    public static final String COLUMN_PAYS = "NAMEPAYS";
    public static final String COLUMN_POP = "POPULATION";
    public static final String COLUMN_URL = "ADRESSE";
    private DBHelper db;
    private SQLiteDatabase database;

    public DAOCapitale(Context context) {
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
    public boolean insertCapitale(String nameCAP, String namePays, String population, String adresse) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CAPITAL, nameCAP);
        contentValues.put(COLUMN_PAYS, namePays);
        contentValues.put(COLUMN_POP, population);
        contentValues.put(COLUMN_URL, adresse);

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
