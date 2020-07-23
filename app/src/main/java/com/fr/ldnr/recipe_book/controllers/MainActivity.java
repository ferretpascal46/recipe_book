package com.fr.ldnr.recipe_book.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fr.ldnr.recipe_book.R;
import com.fr.ldnr.recipe_book.dao.DAOAliment;
import com.fr.ldnr.recipe_book.dao.DAORecipe;
import com.fr.ldnr.recipe_book.model.RecipeObject;
import com.fr.ldnr.recipe_book.utils.DBHelper;
import com.fr.ldnr.recipe_book.viewHolder.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private List<RecipeObject> recipes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // création d'un objet DBHelper pour la création de la base
        DBHelper db = new DBHelper(this);

        // création d'un objet DAORecipe pour lire toutes les données de la database
        final DAORecipe dao1 = new DAORecipe(this);

        // création d'un objet DAOAliment pour lire toutes les données de la database
        final DAOAliment dao2 = new DAOAliment(this);

        // ouverture de la la database en mode lecture seulement
        //dao.openLect();

        boolean isInserted1 = dao1.populateRecipe();
        if (isInserted1==true)
            Toast.makeText(MainActivity.this,"Data Inserted Successfully",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this,"Data Not Inserted", Toast.LENGTH_LONG).show();


        boolean isInserted2 = dao2.populateAliment();
        if (isInserted2==true)
            Toast.makeText(MainActivity.this,"Data Inserted Successfully",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this,"Data Not Inserted", Toast.LENGTH_LONG).show();

        // remplissage de la liste avec toutes les données de la base
        recipes = dao1.getAllRecipe();

        // affichage des recettes en une colonne en mode vertical et deux en mode horizontal
        int displayMode = getResources().getConfiguration().orientation;

        recyclerView = findViewById(R.id.recyclerView);

        if (displayMode == 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }

        recyclerView.setAdapter(new MyAdapter(recipes));

    }

    @Override
    // ajout du menu dans l'action bar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    // gestion des cliks de l'utilisateur sur les boutons du menu
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        // menu ajout recette : affiche un toast "fonction indisponible"
        if (id == R.id.action_setting) {
            Intent secondIntent = new Intent(MainActivity.this, InsertActivity.class);
            startActivity(secondIntent);
        }

        // menu suppression recette : lance l'activité d'insertion dans la database
        if (id == R.id.action_setting2) {
            Intent secondIntent = new Intent(MainActivity.this, DeleteActivity.class);
            startActivity(secondIntent);
        }

        // menu modification recette : lance l'activité de suppression dans la database
        if (id == R.id.action_setting3) {
            Intent secondIntent = new Intent(MainActivity.this, UpdateActivity.class);
            startActivity(secondIntent);
        }

        /* menu modification capitale : lance l'activité de modification dans la database
        if (id == R.id.action_setting4) {
            Intent secondIntent = new Intent(MainActivity.this, UpdateActivity.class);
            startActivity(secondIntent);
        }*/
        return super.onOptionsItemSelected(item);
    }
}
