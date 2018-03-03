package com.bonapp.ujm.myapplication.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bonapp.ujm.myapplication.Controller.fait_la_commande;
import com.bonapp.ujm.myapplication.R;

import java.util.List;

/**
 * Created by Nianfo on 06/02/2018.
 */

public class CommandePlatAdapter extends RecyclerView.Adapter<CommandePlatAdapter.commdViewHolder> {

    private List<Restaurant.Plats> plat;
    public CommandePlatAdapter(fait_la_commande fait_la_commande, List<Restaurant.Plats> list){
        this.plat = list;
    }
    @Override
    public commdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.commande_entre_plat_dessert,parent,false);
        return new commdViewHolder(view);
    }

    @Override
    public void onBindViewHolder(commdViewHolder holder, int position) {
        Restaurant.Plats plats = plat.get(position);
        holder.display(plats);
    }

    @Override
    public int getItemCount() {
        return plat.size();
    }

    public class commdViewHolder extends RecyclerView.ViewHolder{

        Spinner nb_personnes;
        ImageView imageView;
        TextView nom ;

        public commdViewHolder(View view){
            super(view);
            nom = (TextView) view.findViewById(R.id.commandenomPlat);
            nb_personnes =  (Spinner) view.findViewById(R.id.commandeNbpersonnePlat);
            imageView = (ImageView) view.findViewById(R.id.commandeimagePlat);
        }

        public void display(Restaurant.Plats plats){
            nom.setText(plats.nom);
            nb_personnes.setTag(plats.nb_personnes);
            imageView.setImageResource(plats.image);
        }
    }


}