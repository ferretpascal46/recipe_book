package com.fr.ldnr.recipe_book.viewHolder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fr.ldnr.recipe_book.R;
import com.fr.ldnr.recipe_book.model.RecipeObject;
import com.squareup.picasso.Picasso;

import java.io.File;

public class MyViewHolder extends RecyclerView.ViewHolder {

    private TextView textView_cat;
    private TextView textView_name;
    private TextView textView_com;
    private TextView textView_id;
    private ImageView imageView;

    //itemView est la vue correspondante à une cellue
    public MyViewHolder(View itemView) {
        super(itemView);

        // récupération des TextView et de l'ImageView
        textView_cat = itemView.findViewById(R.id.text_cat);
        textView_name = itemView.findViewById(R.id.text_name);
        textView_com = itemView.findViewById(R.id.text_com);
        textView_id = itemView.findViewById(R.id.textView_id);
        imageView = itemView.findViewById(R.id.image);
    }

    /* fonction pour remplir les cellules en fonction d'un MyObject
       chargement de l'image à l'aide de Picasso
     */
    public void bind(RecipeObject myObject) {
        textView_cat.setText(myObject.getRecipe_category());
        textView_name.setText(myObject.getRecipe_title());
        textView_com.setText(myObject.getRecipe_note());
        //Bitmap image = BitmapFactory.decodeFile(myObject.getRecipe_file());
        //Picasso.with(imageView.getContext()).load().centerCrop().fit().into(imageView);
        Bitmap image = BitmapFactory.decodeFile(imageView.getContext().getPackageCodePath()+"/"+myObject.getRecipe_file());
        imageView.setImageBitmap(image);
        textView_id.setText(String.valueOf(myObject.getRecipe_id()));
        //Picasso.with(imageView.getContext()).load("android.resource://com.fr.ldnr.recipe_book/ap_granitr_de_melon_cru.jpg").centerCrop().fit().into(imageView);

        imageView.setImageResource(R.drawable.saumon_abricots);
        //Picasso.with(imageView.getContext()).load(myObject.getRecipe_file()).centerCrop().fit().into(imageView);
    }
}
