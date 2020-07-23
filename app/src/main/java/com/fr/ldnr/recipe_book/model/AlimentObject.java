package com.fr.ldnr.recipe_book.model;

public class AlimentObject {
    private int aliment_id;
    private String aliment_name;

    public AlimentObject(int aliment_id, String aliment_name) {
        this.aliment_id = aliment_id;
        this.aliment_name = aliment_name;
    }

    public int getAliment_id() {
        return aliment_id;
    }

    public void setAliment_id(int aliment_id) {
        this.aliment_id = aliment_id;
    }

    public String getAliment_name() {
        return aliment_name;
    }

    public void setAliment_name(String aliment_name) {
        this.aliment_name = aliment_name;
    }
}
