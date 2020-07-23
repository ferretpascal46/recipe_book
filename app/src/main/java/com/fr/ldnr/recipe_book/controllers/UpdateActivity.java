package com.fr.ldnr.recipe_book.controllers;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        final TextView textPays = findViewById(R.id.nom_pays);
        final TextView textPop = findViewById(R.id.nom_pop);
        final TextView textUrl = findViewById(R.id.nom_url);
        final EditText capital = findViewById(R.id.saisie_capitale);
        final EditText pays = findViewById(R.id.saisie_pays);
        final EditText nombre = findViewById(R.id.saisie_pop);
        final EditText url = findViewById(R.id.saisie_url);
        final Button connexion1 = findViewById(R.id.clic2_button);
        final Button connexion2 = findViewById(R.id.clic_button);
        final TextView error = findViewById(R.id.message);

        error.setText(R.string.message_content2);

        // création d'un objet DAOCapitale pour modifier dans la database
        final DAOCapitale dao = new DAOCapitale(this);
        dao.openLect();

        // gestion du click du bouton modifier
        connexion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gestion des erreurs
                if (capital.getText().toString().isEmpty()) {
                    error.setVisibility(View.VISIBLE);
                    error.setText(R.string.message_content3);
                } else {
                    Cursor rs = dao.getCapital(capital.getText().toString());
                    if (rs.moveToFirst()) {
                        capital.setText(rs.getString(1));
                        pays.setText(rs.getString(2));
                        nombre.setText(rs.getString(3));
                        url.setText(rs.getString(4));

                        textPays.setVisibility(View.VISIBLE);
                        textPop.setVisibility(View.VISIBLE);
                        textUrl.setVisibility(View.VISIBLE);
                        nombre.setVisibility(View.VISIBLE);
                        url.setVisibility(View.VISIBLE);
                        connexion1.setVisibility(View.INVISIBLE);
                        connexion2.setVisibility(View.VISIBLE);
                        pays.setVisibility(View.VISIBLE);
                        error.setText(R.string.message_content5);
                    } else {
                        error.setText(R.string.message_content4);
                    }
                }
            }
        });

        connexion2.setOnClickListener(new View.OnClickListener() {
            final EditText capital = findViewById(R.id.saisie_capitale);
            final EditText pays = findViewById(R.id.saisie_pays);
            final EditText nombre = findViewById(R.id.saisie_pop);
            final EditText url = findViewById(R.id.saisie_url);
            @Override
            public void onClick(View v) {
                //gestion des erreurs
                if (capital.getText().toString().isEmpty() || pays.getText().toString().isEmpty() || url.getText().toString().isEmpty() || nombre.getText().toString().isEmpty()){
                    error.setText(R.string.message_content1);
                } else {
                    // mise à jour de la database, méthode de DAOCapitale
                    boolean isUpdate = dao.updateCapital(capital.getText().toString(),
                            pays.getText().toString(), nombre.getText().toString(), url.getText().toString());

                    // contrôle de la modification dans la database
                    if (isUpdate==true)
                        Toast.makeText(UpdateActivity.this,"Data Update Successfully", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(UpdateActivity.this,"Data Not Update", Toast.LENGTH_LONG).show();
                    dao.close();

                    // retour à la vue principale
                    Intent secondIntent = new Intent(UpdateActivity.this, MainActivity.class);
                    startActivity(secondIntent);
                }
            }
        });

    }
}
