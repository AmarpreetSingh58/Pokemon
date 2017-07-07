package com.amarpreetsinghprojects.pokemon;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by kulvi on 07/05/17.
 */

public class PokeAdapter extends RecyclerView.Adapter<PokeAdapter.PokeViewHolder> {

    ArrayList<PokeList> pokeListArrayList;
    Context c;

    public PokeAdapter(ArrayList<PokeList> pokeListArrayList) {
        this.pokeListArrayList = pokeListArrayList;
    }

    @Override
    public PokeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (LayoutInflater.from(parent.getContext())).inflate(R.layout.pokelist_single_item,parent,false);
        c = parent.getContext();
        return new PokeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PokeViewHolder holder, int position) {
        PokeList pokeList = pokeListArrayList.get(position);
        holder.name.setText(pokeList.getName());
        holder.weight.setText(pokeList.getWeight());
        holder.height.setText(pokeList.getHeight());
        Picasso.with(c).load(pokeList.getSprites().front_default).resize(1000,1000).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return pokeListArrayList.size();
    }


    public class PokeViewHolder extends RecyclerView.ViewHolder {
        TextView name,weight,height;
        ImageView imageView;
        public PokeViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.nameTV);
            weight = (TextView)itemView.findViewById(R.id.weightTV);
            height = (TextView)itemView.findViewById(R.id.heightTV);
            imageView = (ImageView)itemView.findViewById(R.id.imageTV);
        }
    }
}
