package com.fr.ldnr.recipe_book.controllers;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fr.ldnr.recipe_book.R;
import com.fr.ldnr.recipe_book.dao.DAORecipe;
import com.fr.ldnr.recipe_book.utils.DBHelper;
import com.fr.ldnr.recipe_book.viewHolder.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private List<MyObject> cities = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // création d'un objet DBHelper pour la création de la base
        DBHelper db = new DBHelper(this);

        // création d'un objet DAORecipe pour lire toutes les données de la database
        final DAORecipe dao = new DAORecipe(this);

        // ouverture de la la database en mode lecture seulement
        dao.openLect();

        /* insertion et vérification des 6 premières capitales
        boolean isInserted = dao.insertCapitale("Paris", "France", "2,148 millions", "https://www.telegraph.co.uk/travel/destination/article130148.ece/ALTERNATES/w620/parisguidetower.jpg");
        dao.insertCapitale("Londres", "Angleterre", "8,982 millions","https://cdn.londonandpartners.com/visit/london-organisations/tower-bridge/86830-640x360-tower-bridge-640.jpg");
        dao.insertCapitale("Berlin", "Allemagne", "3,769 millions","https://geo.img.pmdstatic.net/fit/http.3A.2F.2Fprd2-bone-image.2Es3-website-eu-west-1.2Eamazonaws.2Ecom.2Fgeo.2F2019.2F12.2F17.2F3b327c02-dc45-4417-adfb-f506c085d51e.2Ejpeg/1120x630/background-color/ffffff/focus-point/798%2C572/quality/70/berlin-10-escales-au-fil-de-leau.jpg");
        dao.insertCapitale("Madrid", "Espagne", "6,642 millions","https://thegoodlife.thegoodhub.com/wp-content/thumbnails/uploads/sites/2/2018/08/numerisation-tgl-35-good-escape-madrid-coeur-2-77-stevens-fremont-tt-width-980-height-796-crop-1-bgcolor-ffffff.jpg");
        dao.insertCapitale("Rome", "Italie", "2,873 millions","http://retouralinnocence.com/wp-content/uploads/2013/05/Hotel-en-Italie-pour-les-Vacances2.jpg");
        dao.insertCapitale("Moscou", "Russie",  "11,92 millions","https://russieautrement.com/upload/medialibrary/3f4/moscou-2.jpg");
        if (isInserted==true)
            Toast.makeText(MainActivity.this,"Data Inserted Successfully",Toast.LENGTH_LONG).show();
         else
            Toast.makeText(MainActivity.this,"Data Not Inserted", Toast.LENGTH_LONG).show();
        */

        // remplissage de la liste avec toutes les données de la base
        cities = dao.getAllData();

        // affichage des capitales en une colonne en mode vertical et deux en mode horizontal
        int displayMode = getResources().getConfiguration().orientation;

        recyclerView = findViewById(R.id.recyclerView);

        if (displayMode == 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }

        recyclerView.setAdapter(new MyAdapter(cities));

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

        // menu ajout capitale : affiche un toast "fonction indisponible"
        if (id == R.id.action_setting) {
            Toast toast = Toast.makeText(getApplicationContext(), "Fonction indisponible", Toast.LENGTH_LONG);

            // personnalisation du toast
            View toastview = toast.getView();
            TextView tv = (TextView) toastview.findViewById(android.R.id.message);

            tv.setTextSize(25);
            tv.setTextColor(Color.DKGRAY);
            toastview.setBackgroundColor(Color.LTGRAY);

            tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_launcher_foreground, 0, 0, 0);
            tv.setCompoundDrawablePadding(20);

            toast.show();
        }

        // menu ajout capitale : lance l'activité d'insertion dans la database
        if (id == R.id.action_setting2) {
            Intent secondIntent = new Intent(MainActivity.this, InsertActivity.class);
            startActivity(secondIntent);
        }

        // menu suppression capitale : lance l'activité de suppression dans la database
        if (id == R.id.action_setting3) {
            Intent secondIntent = new Intent(MainActivity.this, DeleteActivity.class);
            startActivity(secondIntent);
        }

        // menu modification capitale : lance l'activité de modification dans la database
        if (id == R.id.action_setting4) {
            Intent secondIntent = new Intent(MainActivity.this, UpdateActivity.class);
            startActivity(secondIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
