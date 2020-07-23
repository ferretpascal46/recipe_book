package com.fr.ldnr.recipe_book.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fr.ldnr.recipe_book.R;
import com.fr.ldnr.recipe_book.model.RecipeObject;
import com.fr.ldnr.recipe_book.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class DAORecipe {

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

    // fonction

    /**
     * insertion d'une recette
     *
     * @param recipeObject
     * @return
     */
    public boolean insertRecipe(RecipeObject recipeObject) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.RECIPE_TITLE, recipeObject.getRecipe_title());
        contentValues.put(DBHelper.RECIPE_NOTE, recipeObject.getRecipe_note());
        contentValues.put(DBHelper.RECIPE_CATEGORY, recipeObject.getRecipe_category());
        contentValues.put(DBHelper.RECIPE_FILE, recipeObject.getRecipe_file());

        if (database.insert(DBHelper.TABLE_RECIPE, null, contentValues) == -1) {
            return false;
        } else return true;
    }

    /**
     * suppression d'une recette
     *
     * @param nom
     * @return
     */
    public boolean deleteRecipe(String nom) {
        if (database.delete(DBHelper.TABLE_RECIPE, DBHelper.RECIPE_TITLE + "=?", new String[]{nom}) == 0) {
            return false;
        } else return true;
    }
    /**
     * suppression d'une recette
     *
     * @param id
     * @return
     */
    public boolean deleteRecipeById(int id) {
        if (database.delete(DBHelper.TABLE_RECIPE, DBHelper.RECIPE_ID + "=?",new String[]{String.valueOf(id)} ) == 0) {
            return false;
        } else return true;
    }

    /**
     * récupérer une recette
     *
     * @param nom
     * @return
     */
    public Cursor getRecipe(String nom) {
        String request = "SELECT * FROM " + DBHelper.TABLE_RECIPE +
                " WHERE " + DBHelper.RECIPE_TITLE + " = '" + nom + "';";
        return database.rawQuery(request, null);
    }


    /**
     * récupérer toutes les recettes
     *
     * @return
     */
    public List<RecipeObject> getAllRecipe() {

        List<RecipeObject> recipes = new ArrayList<>();
        String request = "SELECT * FROM " + DBHelper.TABLE_RECIPE;
        Cursor result = database.rawQuery(request, null);

        while (result.moveToNext()) {
            recipes.add(new RecipeObject(
                    result.getInt(0),
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4))
            );
        }

        result.close();
        return recipes;
    }

    /**
     * récupérer toutes les recettes en fonction de la category
     *
     * @param category
     * @return
     */
    public List<RecipeObject> getAllRecipeByCategory(String category) {

        List<RecipeObject> recipes = new ArrayList<>();
        String request = "SELECT * FROM " + DBHelper.TABLE_RECIPE +
                " WHERE " + DBHelper.RECIPE_CATEGORY + " = '" + category + "';";
        Cursor result = database.rawQuery(request, null);

        while (result.moveToNext()) {
            recipes.add(
                    new RecipeObject(
                            result.getInt(0),
                            result.getString(1),
                            result.getString(2),
                            result.getString(3),
                            result.getInt(4))
            );
        }

        result.close();
        return recipes;
    }


    /**
     * récupérer toutes les recettes en fonction de l'aliment
     *
     * @param aliment
     * @return
     */
    public List<RecipeObject> getAllRecipeByAliments(String aliment) {

        List<RecipeObject> recipes = new ArrayList<>();

        String request = "SELECT "
                + DBHelper.RECIPE_ID + ","
                + DBHelper.RECIPE_TITLE + ","
                + DBHelper.RECIPE_NOTE + ","
                + DBHelper.RECIPE_CATEGORY + ","
                + DBHelper.RECIPE_FILE

                + " FROM "
                + DBHelper.TABLE_RECIPE + ", "
                + DBHelper.TABLE_ALIMENT + " AND "
                + DBHelper.TABLE_ASSOCIATION_REC_ALI

                + " WHERE '" + DBHelper.RECIPE_ID + "' = '" + DBHelper.FK_RECIPE + "'"
                + " AND '" + DBHelper.ALIMENT_ID + "' = '" + DBHelper.FK_ALIMENT + "';"
                + " AND '" + DBHelper.ALIMENT_NAME + "' = '" + aliment.toUpperCase() + "';";

        Cursor result = database.rawQuery(request, null);

        while (result.moveToNext()) {
            recipes.add(
                    new RecipeObject(
                            result.getInt(0),
                            result.getString(1),
                            result.getString(2),
                            result.getString(3),
                            result.getInt(4))
            );
        }

        result.close();
        return recipes;
    }

    /**
     * modification d'une recette
     *
     * @param recipe_id
     * @param recipe_title
     * @param recipe_note
     * @param recipe_category
     * @param recipe_file
     * @return
     */
    public boolean updateRecipe(int recipe_id, String recipe_title, String recipe_note, String recipe_category,
                                String recipe_file) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.RECIPE_ID, recipe_id);
        contentValues.put(DBHelper.RECIPE_TITLE, recipe_title);
        contentValues.put(DBHelper.RECIPE_NOTE, recipe_note);
        contentValues.put(DBHelper.RECIPE_CATEGORY, recipe_category);
        contentValues.put(DBHelper.RECIPE_FILE, recipe_file);


        if (database.update(DBHelper.TABLE_RECIPE, contentValues, DBHelper.RECIPE_TITLE + "=?", new String[]{recipe_title}) > 0) {
            return true;
        } else return false;
    }

    /**
     * récupérer le nombre d'occurence de la table
     *
     * @return
     */
    public int numbersOfRows() {
        openLect();
        return (int) DatabaseUtils.queryNumEntries(database, DBHelper.TABLE_RECIPE);
    }

    /**
     * inserer quelques recette dans la base de donnée
     *
     * @return
     */
    public boolean populateRecipe() {
        boolean complete = true;
        if (numbersOfRows() == 0) {
            boolean isInserted0 = insertRecipe(new RecipeObject("Gougère au comté", "", "Apéritif", R.drawable.ap_gougere_comte));
            if (!isInserted0) complete = false;

            boolean isInserted1 = insertRecipe(new RecipeObject("Granité de melon et chips de jambon cru", "", "Apéritif", R.drawable.ap_granite_melon));
            if (!isInserted1) complete = false;

            boolean isInserted2 = insertRecipe(new RecipeObject("Madeleine courgettes chorizo", "", "Apéritif", R.drawable.ap_madeleine_chorizo));
            if (!isInserted2) complete = false;

            boolean isInserted3 = insertRecipe(new RecipeObject("Tapenade aux olives vertes", "", "Apéritif", R.drawable.ap_tapenade_thon));
            if (!isInserted3) complete = false;

            boolean isInserted4 = insertRecipe(new RecipeObject("Cocktail passion bière", "", "Cocktail", R.drawable.cock_passion_biere));
            if (!isInserted4) complete = false;

            boolean isInserted5 = insertRecipe(new RecipeObject("Cheescake framboise", "", "Dessert", R.drawable.des_cheescake_framboise));
            if (!isInserted5) complete = false;

            boolean isInserted6 = insertRecipe(new RecipeObject("Crumble aux pommes", "", "Dessert", R.drawable.des_crumble_pommes));
            if (!isInserted6) complete = false;

            boolean isInserted7 = insertRecipe(new RecipeObject("Framboisier à emporter", "", "Dessert", R.drawable.des_framboisier));
            if (!isInserted7) complete = false;

            boolean isInserted8 = insertRecipe(new RecipeObject("Tarte banane et noix de coco", "", "Dessert", R.drawable.des_tartebanane));
            if (!isInserted8) complete = false;

            boolean isInserted9 = insertRecipe(new RecipeObject("Pommes de terre à la suédoise", "", "Légumes", R.drawable.leg_pommedeterre_suedoise));
            if (!isInserted9) complete = false;

            boolean isInserted10 = insertRecipe(new RecipeObject("Dos de cabillaud et crumble", "", "Poisson", R.drawable.po_cabillaud_crumble));
            if (!isInserted10) complete = false;

            boolean isInserted11 = insertRecipe(new RecipeObject("Crevettes et St Jacques", "", "Poisson", R.drawable.po_crevettes_saintjacques));
            if (!isInserted11) complete = false;

            boolean isInserted12 = insertRecipe(new RecipeObject("Gambas à la plancha", "", "Poisson", R.drawable.po_gambas_plancha));
            if (!isInserted12) complete = false;

            boolean isInserted13 = insertRecipe(new RecipeObject("Papillote au saumon et courgettes", "", "Poison", R.drawable.po_papillote_saumon));
            if (!isInserted13) complete = false;

            boolean isInserted14 = insertRecipe(new RecipeObject("Salade Mangue avocat crevettes", "", "Salade", R.drawable.sal_mangue));
            if (!isInserted14) complete = false;

            boolean isInserted15 = insertRecipe(new RecipeObject("Tarte au roquefort", "", "Tarte salée", R.drawable.ts_roquefort_poire));
            if (!isInserted15) complete = false;

            boolean isInserted16 = insertRecipe(new RecipeObject("Filet de boeuf au foie gras", "", "Viande", R.drawable.vi_filetboeuf_foiegras));
            if (!isInserted16) complete = false;

            boolean isInserted17 = insertRecipe(new RecipeObject("Escalope de dinde marinée", "", "Viande", R.drawable.vi_marinade_dinde));
            if (!isInserted17) complete = false;

            boolean isInserted18 = insertRecipe(new RecipeObject("Poulet basquaise", "", "Viande", R.drawable.vi_poulet_basquaise));
            if (!isInserted18) complete = false;

            boolean isInserted19 = insertRecipe(new RecipeObject("Tajine de poulet aux citrons confits", "", "Viande", R.drawable.vi_tajine_poulet));
            if (!isInserted19) complete = false;
        }
        return complete;
    }
}


