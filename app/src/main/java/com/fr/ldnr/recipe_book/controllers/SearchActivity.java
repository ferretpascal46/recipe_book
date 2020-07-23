package com.fr.ldnr.recipe_book.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.fr.ldnr.recipe_book.R;
import com.fr.ldnr.recipe_book.dao.DAORecipe;
import com.fr.ldnr.recipe_book.model.RecipeObject;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private List<String> recipesCategories = new ArrayList<>();
    DAORecipe daoRecipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final Intent intent = getIntent();

        if (intent.hasExtra("recipe_name")){
            daoRecipe = (DAORecipe) intent.getSerializableExtra("recipe_name");
        }


        //-- Drop Down REGION LIST
        final Spinner spinnerRegion = (Spinner) findViewById(R.id.spinner);

        //ArrayAdapter<String> dataAdapterR = new ArrayAdapter(this,android.R.layout.simple_spinner_item, recipesCategories );
        //dataAdapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinnerRegion.setAdapter(dataAdapterR);
    }
}