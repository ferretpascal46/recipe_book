package com.fr.ldnr.recipe_book.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fr.ldnr.recipe_book.R;
import com.squareup.picasso.Picasso;

public class MyViewHolder extends RecyclerView.ViewHolder {

    private TextView textViewView;
    private TextView textView_pays;
    private TextView textView_pop;
    private ImageView imageView;

    //itemView est la vue correspondante à une cellue
    public MyViewHolder(View itemView) {
        super(itemView);

        // récupération des TextView et de l'ImageView
        textViewView = itemView.findViewById(R.id.text);
        textView_pays = itemView.findViewById(R.id.text_pays);
        textView_pop = itemView.findViewById(R.id.text_pop);
        imageView = itemView.findViewById(R.id.image);
    }

    /* fonction pour remplir les cellules en fonction d'un MyObject
       chargement de l'image à l'aide de Picasso
     */
    public void bind(MyObject myObject) {
        textViewView.setText(myObject.getText());
        textView_pays.setText(myObject.getText_pays());
        textView_pop.setText(myObject.getText_pop());
        Picasso.with(imageView.getContext()).load(myObject.getImageUrl()).centerCrop().fit().into(imageView);
    }
}
