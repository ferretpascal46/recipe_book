package com.fr.ldnr.recipe_book;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("TEST", "Pascal test ");
        Log.d("TEST", "Michel test2 ");
        Log.d("TEST", "Albane test3 ");


    }
}