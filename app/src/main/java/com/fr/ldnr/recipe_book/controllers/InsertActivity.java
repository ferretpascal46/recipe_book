package com.fr.ldnr.recipe_book.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fr.ldnr.recipe_book.R;
import com.fr.ldnr.recipe_book.dao.DAORecipe;
import com.fr.ldnr.recipe_book.model.RecipeObject;

public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        final EditText et_recipe_name = findViewById(R.id.etNewRecipe);
        final EditText et_recipe_category = findViewById(R.id.etCategory);
        final EditText et_recipe_file = findViewById(R.id.etImage);
        final Button btn_insert = findViewById(R.id.AddButton);
        final TextView et_error = findViewById(R.id.message);

        /**
         * TODO: changer le message content
         */
        //error.setText(R.string.message_content);

        // création d'un objet DAORecipe pour insérer dans la database
        final DAORecipe dao = new DAORecipe(this);
        dao.open();

        // gestion du click du bouton ajouter
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gestion des erreurs
                if (et_recipe_name.getText().toString().isEmpty()
                        || et_recipe_category.getText().toString().isEmpty()
                        || et_recipe_file.getText().toString().isEmpty()) {

                    et_error.setText(R.string.message_content1);
                } else {
                    // insertion dans la database, méthode de DAORecipe
                    boolean isInserted = dao.insertRecipe(new RecipeObject(et_recipe_name.getText().toString(), ""
                            , et_recipe_category.getText().toString(), et_recipe_file.getText().toString()));

                    // contrôle de l'insertion dans la database
                    if (isInserted==true)
                        Toast.makeText(InsertActivity.this,"Data Inserted Successfully", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(InsertActivity.this,"Data Not Inserted", Toast.LENGTH_LONG).show();
                    dao.close();

                    // retour à la vue principale
                    Intent backIntent = new Intent(InsertActivity.this, MainActivity.class);
                    startActivity(backIntent);
                }
            }
        });
    }
}
