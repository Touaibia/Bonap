package com.bonapp.ujm.myapplication.Model;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bonapp.ujm.myapplication.Controller.PageRestaurant;
import com.bonapp.ujm.myapplication.R;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Nianfo on 23/01/2018.
 */

public class SuggestionRestoAdapter extends RecyclerView.Adapter<SuggestionRestoAdapter.RestoViewHolder> {

    List<Restaurant> list;


    public SuggestionRestoAdapter(List<Restaurant> list){
        this.list = list;
    }
    @Override
    public RestoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.restaurants,parent,false);
        return new RestoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestoViewHolder holder, int position) {

        Restaurant rest = this.list.get(position);
        holder.display(rest);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class RestoViewHolder extends RecyclerView.ViewHolder {
       public TextView nom;
       public ImageView img;
       public Restaurant current;

        public RestoViewHolder(final View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.NomRest);
            img = (ImageView) itemView.findViewById(R.id.imageRestau);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), PageRestaurant.class);
                    startActivity(view.getContext(),intent, null);

                }
            });
        }

        //@SuppressLint("ResourceType")
        public void display(Restaurant rest){
            current = rest;
            nom.setText(rest.nom);
            img.setImageResource(rest.image);
        }
    }
}
