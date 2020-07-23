package com.fr.ldnr.recipe_book.model;

public class RecipeObject {
    private int recipe_id;
    private String recipe_title;
    private String recipe_note;
    private String recipe_category;
    private int recipe_file;



    public RecipeObject(int recipe_id, String recipe_title, String recipe_note, String recipe_category, int recipe_file) {
        this.recipe_id = recipe_id;
        this.recipe_title = recipe_title;
        this.recipe_note = recipe_note;
        this.recipe_category = recipe_category;
        this.recipe_file = recipe_file;
    }
    public RecipeObject( String recipe_title, String recipe_note, String recipe_category, int recipe_file) {
        this.recipe_id = 0;
        this.recipe_title = recipe_title;
        this.recipe_note = recipe_note;
        this.recipe_category = recipe_category;
        this.recipe_file = recipe_file;
    }


    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getRecipe_title() {
        return recipe_title;
    }

    public void setRecipe_title(String recipe_title) {
        this.recipe_title = recipe_title;
    }

    public String getRecipe_note() {
        return recipe_note;
    }

    public void setRecipe_note(String recipe_note) {
        this.recipe_note = recipe_note;
    }

    public String getRecipe_category() {
        return recipe_category;
    }

    public void setRecipe_category(String recipe_category) {
        this.recipe_category = recipe_category;
    }

    public int getRecipe_file() {
        return recipe_file;
    }

    public void setRecipe_file(int recipe_file) {
        this.recipe_file = recipe_file;
    }

}
