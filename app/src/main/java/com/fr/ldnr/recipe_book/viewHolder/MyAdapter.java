package com.fr.ldnr.recipe_book.viewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.fr.ldnr.recipe_book.R;
import com.fr.ldnr.recipe_book.model.MyObject;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    List<MyObject> list;

    // constructeur qui prend en entrée une liste
    public MyAdapter(List<MyObject> list) {
        this.list = list;
    }

    @Override
    // fonction de création des  viewHolder qui indique la vue à inflater
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cap_cards, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    /* remplissage de la cellule avec le texte/image de chaque MyObject
        appel à la fonction bind de MyViewHolder
     */
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        MyObject myObject = list.get(position);
        myViewHolder.bind(myObject);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

