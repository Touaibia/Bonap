package com.bonapp.ujm.myapplication.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bonapp.ujm.myapplication.Controller.CarteMenu;
import com.bonapp.ujm.myapplication.Controller.fait_la_commande;
import com.bonapp.ujm.myapplication.R;

import java.util.List;

/**
 * Created by Nianfo on 21/01/2018.
 */

public class CarteMenuPlatAdapter extends RecyclerView.Adapter<CarteMenuPlatAdapter.carteMenuPlatViewHolder> {
    private List<Restaurant.Plats> plat;
    private Context context;
    private LayoutInflater inflater;
    public CarteMenuPlatAdapter (CarteMenu context, List<Restaurant.Plats> list){
        this.plat = list;
    }
    @Override
    public carteMenuPlatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.un_plat,parent,false);
        return new carteMenuPlatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(carteMenuPlatViewHolder holder, int position) {
        Restaurant.Plats plats = plat.get(position);
        holder.display(plats);
    }

    @Override
    public int getItemCount() {
        return plat.size();
    }

    public class carteMenuPlatViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nom;

        public carteMenuPlatViewHolder(View view) {
            super(view);
            nom = (TextView) view.findViewById(R.id.nomPlat);
            imageView = (ImageView) view.findViewById(R.id.imagePlat);
        }

        public void display(Restaurant.Plats plats) {
            nom.setText(plats.nom);
            imageView.setImageResource(plats.image);
        }
    }
}