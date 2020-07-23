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

public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        final EditText capital = findViewById(R.id.saisie_capitale);
        final EditText pays = findViewById(R.id.saisie_pays);
        final EditText nobble = findViewById(R.id.saisie_pop);
        final EditText url = findViewById(R.id.saisie_url);
        final Button connexion = findViewById(R.id.clic_button);
        final TextView error = findViewById(R.id.message);

        /**
         * TODO: changer le message content
         */
        //error.setText(R.string.message_content);

        // création d'un objet DAORecipe pour insérer dans la database
        final DAORecipe dao = new DAORecipe(this);
        dao.open();

        // gestion du click du bouton ajouter
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gestion des erreurs
                if (capital.getText().toString().isEmpty() || pays.getText().toString().isEmpty() || url.getText().toString().isEmpty()) {
                    /**
                     * TODO:changer le message content
                     */
                    //error.setText(R.string.message_content1);
                } else {
                    // insertion dans la database, méthode de DAORecipe
                    boolean isInserted = dao.insertCapitale(capital.getText().toString(),
                            pays.getText().toString(), nobble.getText().toString(), url.getText().toString());

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
