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
            boolean isInserted0 = insertAliment("CHORIZO");
            if (!isInserted0) complete = false;
            boolean isInserted6 = insertAliment("THON");
            if (!isInserted6) complete = false;
            boolean isInserted7 = insertAliment("ANCHOIS");
            if (!isInserted7) complete = false;
            boolean isInserted8 = insertAliment("BIERE");
            if (!isInserted8) complete = false;
            boolean isInserted9 = insertAliment("FRUITS DE LA PASSION");
            if (!isInserted9) complete = false;
            boolean isInserted10 = insertAliment("FRAMBOISES");
            if (!isInserted10) complete = false;
            boolean isInserted11 = insertAliment("FRAISES");
            if (!isInserted11) complete = false;
            boolean isInserted12 = insertAliment("POMMES");
            if (!isInserted12) complete = false;
            boolean isInserted13 = insertAliment("MASCARPONE");
            if (!isInserted13) complete = false;
            boolean isInserted14 = insertAliment("BANANES");
            if (!isInserted14) complete = false;
            boolean isInserted15 = insertAliment("NOIX DE COCO");
            if (!isInserted15) complete = false;
            boolean isInserted16 = insertAliment("AUBERGINES");
            if (!isInserted16) complete = false;
            boolean isInserted17 = insertAliment("MOZZARELLA");
            if (!isInserted17) complete = false;
            boolean isInserted18 = insertAliment("POMMES DE TERRE");
            if (!isInserted18) complete = false;
            boolean isInserted19 = insertAliment("FROMAGE RACLETTE");
            if (!isInserted19) complete = false;
            boolean isInserted20 = insertAliment("CABILLAUD");
            if (!isInserted20) complete = false;
            boolean isInserted21 = insertAliment("PARMESAN");
            if (!isInserted21) complete = false;
            boolean isInserted22 = insertAliment("NOIX DE ST JACQUES");
            if (!isInserted22) complete = false;
            boolean isInserted23 = insertAliment("CREVETTES");
            if (!isInserted23) complete = false;
            boolean isInserted24 = insertAliment("GAMBAS");
            if (!isInserted24) complete = false;
            boolean isInserted25 = insertAliment("GINGEMBRE");
            if (!isInserted25) complete = false;
            boolean isInserted26 = insertAliment("SAUMON");
            if (!isInserted26) complete = false;
            boolean isInserted27 = insertAliment("ROQUETTE");
            if (!isInserted27) complete = false;
            boolean isInserted28 = insertAliment("MANGUE");
            if (!isInserted28) complete = false;
            boolean isInserted29 = insertAliment("ROQUEFORT");
            if (!isInserted29) complete = false;
            boolean isInserted30 = insertAliment("POIRE");
            if (!isInserted30) complete = false;
            boolean isInserted31 = insertAliment("BOEUF");
            if (!isInserted31) complete = false;
            boolean isInserted32 = insertAliment("FOIE GRAS");
            if (!isInserted32) complete = false;
            boolean isInserted33 = insertAliment("DINDE");
            if (!isInserted33) complete = false;
            boolean isInserted34 = insertAliment("SALADE");
            if (!isInserted34) complete = false;
            boolean isInserted35 = insertAliment("POULET");
            if (!isInserted35) complete = false;
            boolean isInserted36 = insertAliment("TOMATES");
            if (!isInserted36) complete = false;
            boolean isInserted37 = insertAliment("ABRICOTS");
            if (!isInserted37) complete = false;
        }

        return complete;
    }
}

