package com.bonapp.ujm.myapplication.Model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bonapp.ujm.myapplication.Controller.MaCarteMenu;
import com.bonapp.ujm.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maham on 19/02/2018.
 */

public class GestionPlatAdapter extends RecyclerView.Adapter<GestionPlatAdapter.MyViewHolder> {
    List<Restaurant.Plats> plats;

    public GestionPlatAdapter(MaCarteMenu maCarteMenu, List<Restaurant.Plats> list) {
        this.plats = list;
    }

    @Override
    public int getItemCount() {
        return plats.size();
    }

    @Override
    public GestionPlatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.gestion_plat, parent, false);
        return new GestionPlatAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Restaurant.Plats plat = plats.get(position);

        holder.optPlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup = new PopupMenu(view.getContext(), holder.optPlat);

                popup.inflate(R.menu.option_plat);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.mod_plat:
                                //Affichage du formulaire de modification
                                break;
                            case R.id.sup_plat:
                                //Suppression du plat
                                break;

                        }
                        return false;
                    }
                });

                popup.show();
            }
        });

        holder.display(plat);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nom;
        TextView optPlat;

        public MyViewHolder(View view) {
            super(view);
            nom = (TextView) view.findViewById(R.id.nom_plat);
            imageView = (ImageView) view.findViewById(R.id.img_plat);
            optPlat = (TextView) view.findViewById(R.id.plat_opt);
        }

        public void display(Restaurant.Plats plat) {
            nom.setText(plat.nom);
            imageView.setImageResource(plat.image);
        }
    }

}
