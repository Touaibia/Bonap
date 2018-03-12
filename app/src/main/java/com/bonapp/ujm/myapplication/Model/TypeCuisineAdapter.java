package com.bonapp.ujm.myapplication.Model;

import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bonapp.ujm.myapplication.Controller.GestionProfil;
import com.bonapp.ujm.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by maham on 12/02/2018.
 */

public class TypeCuisineAdapter extends RecyclerView.Adapter<TypeCuisineAdapter.MyViewHolder> {

    private ArrayList<TypeCuisine> lesTypes;

    public TypeCuisineAdapter(ArrayList<TypeCuisine> lesTypes) {
        this.lesTypes = lesTypes;
    }

    @Override
    public int getItemCount() {
        return lesTypes.size();
    }

    //Ajout d'un nouveau type de cuisine
    public void addType(TypeCuisine type){
        lesTypes.add(type);
    }

    //Suppression d'un type de cuisine
    public void deleteType(TypeCuisine type){
        lesTypes.remove(type);
    }

    @Override
    public TypeCuisineAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.types_cuisines, parent, false);
        return new TypeCuisineAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        TypeCuisine type = lesTypes.get(position);

        holder.boutonSup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteType(holder.leType);
            }
        });

        holder.display(type);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView typeCuisine;
        private Button boutonSup;
        private TypeCuisine leType;

        public MyViewHolder(View itemView){
            super(itemView);

            typeCuisine = (TextView) itemView.findViewById(R.id.type_cuis);
            boutonSup = (Button) itemView.findViewById(R.id.sup_type_cuis);
        }

        public void display(TypeCuisine pair) {
            leType = pair;
            typeCuisine.setText(leType.getNom());
        }

    }
}
