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

import com.fr.ldnr.recipe_book.R;
import com.fr.ldnr.recipe_book.dao.DAOAliment;
import com.fr.ldnr.recipe_book.dao.DAORecipe;
import com.fr.ldnr.recipe_book.model.RecipeObject;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        final Intent intent = getIntent();


        final TextView tv_recipe    = findViewById(R.id.etUpdateRecipe_name);
        final TextView tv_category  = findViewById(R.id.etUpdateRecipe_category);
        final TextView tv_note      = findViewById(R.id.etUpdate_comments);
        final Button btn_update     = findViewById(R.id.upDatBeutton);
        final TextView error        = findViewById(R.id.error);


        error.setText(R.string.message_content2);

        //création d'un objet DAORecipe pour modifier dans la database
        final DAORecipe daoRecipe = new DAORecipe(this);

        // TODO: utiliser la daoAliment un jour
        final DAOAliment daoAliment = new DAOAliment(this);


        daoRecipe.openLect();

        if (intent.hasExtra("recipe_name")){
            tv_recipe.setText(intent.getStringExtra("recipe_name"));
        }
        if (intent.hasExtra("recipe_note")){
            tv_note.setText(intent.getStringExtra("recipe_note"));
        }
        if (intent.hasExtra("recipe_category")){
            tv_category.setText(intent.getStringExtra("recipe_category"));
        }


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gestion des erreurs
                if (tv_recipe.getText().toString().isEmpty()
                        || tv_category.getText().toString().isEmpty()){

                    error.setText(R.string.message_content1);
                } else {
                    // mise à jour de la database, méthode de DAORecipe
                    boolean isUpdate = daoRecipe.updateRecipeExeptFile(
                            new RecipeObject(
                                    Integer.parseInt(intent.getStringExtra("recipe_id")),
                                    tv_recipe.getText().toString(),
                                    tv_note.getText().toString(),
                                    tv_category.getText().toString(),
                                    Integer.parseInt(intent.getStringExtra("recipe_file"))
                                    )
                    );

                    // contrôle de la modification dans la database
                    if (isUpdate==true)
                        Toast.makeText(UpdateActivity.this,"Data Update Successfully", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(UpdateActivity.this,"Data Not Update", Toast.LENGTH_LONG).show();
                    daoRecipe.close();
                    Intent mainIntent = new Intent(UpdateActivity.this, MainActivity.class);
                    // retour à la vue principale
                    finish();
                    startActivity(mainIntent);
                }
            }
        });

    }
}
