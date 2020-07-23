package com.fr.ldnr.recipe_book.model;

public class MyObject {
    private String text;
    private String text_pays;
    private String text_pop;
    private String imageUrl;

    public MyObject(String text, String text_pays, String text_pop, String imageUrl) {
        this.text = text;
        this.text_pays = text_pays;
        this.text_pop = text_pop;
        this.imageUrl = imageUrl;
    }

    public void setText_pays(String text_pays) {
        this.text_pays = text_pays;
    }

    public void setText_pop(String text_pop) {
        this.text_pop = text_pop;
    }

    public String getText_pays() {
        return text_pays;
    }

    public String getText_pop() {
        return text_pop;
    }

    public String getText() {
        return text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
