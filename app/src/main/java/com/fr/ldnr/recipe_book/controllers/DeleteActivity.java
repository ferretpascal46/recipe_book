package com.fr.ldnr.recipe_book.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fr.ldnr.recipe_book.R;
import com.fr.ldnr.recipe_book.dao.DAORecipe;

public class DeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        final EditText recipes = findViewById(R.id.saisie_capitale);
        final Button connexion = findViewById(R.id.clic_button2);

        // création d'un objet DAORecipe pour supprimer dans la database
        final DAORecipe dao = new DAORecipe(this);

        //ouverture en mode lecture/écriture
        dao.open();

        // gestion du click du bouton supprimer
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // suppression dans la database, méthode de DAORecipe
                boolean isDeleted = dao.deleteRecipe(recipes.getText().toString());

                // contrôle de la suppression dans la database
                if (isDeleted == false)
                    Toast.makeText(DeleteActivity.this, "Data Not Deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(DeleteActivity.this, "Data Deleted Successfully", Toast.LENGTH_LONG).show();

                dao.close();

                // retour à la vue principale
                Intent backIntent = new Intent(DeleteActivity.this, MainActivity.class);
                startActivity(backIntent);
            }
        });
    }
}


