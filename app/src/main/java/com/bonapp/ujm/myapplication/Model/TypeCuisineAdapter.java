package com.bonapp.ujm.myapplication.Model;

import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bonapp.ujm.myapplication.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by maham on 12/02/2018.
 */

public class TypeCuisineAdapter extends RecyclerView.Adapter<TypeCuisineAdapter.MyViewHolder> {

    private List<Pair<String,Integer>> lesTypes = Arrays.asList(
            Pair.create("Lyra Belacqua", 0),
            Pair.create("Pantalaimon", 1),
            Pair.create("Roger Parslow", 2),
            Pair.create("Lord Asriel", 3),
            Pair.create("Marisa Coulter", 4)
    );

    @Override
    public int getItemCount() {
        return lesTypes.size();
    }

    //Ajout d'un nouveau type de cuisine
    public void addType(Pair<String, Integer> pair){
        lesTypes.add(pair);
    }

    //Suppression d'un type de cuisine
    public void deleteType(int position){
        lesTypes.remove(position);
    }

    @Override
    public TypeCuisineAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.types_cuisines, parent, false);
        return new TypeCuisineAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pair<String, Integer> pair = lesTypes.get(position);
        holder.display(pair);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView typeCuisine;
        private TextView boutonSup;
        private Pair<String,Integer> leType;

        public MyViewHolder(View itemView){
            super(itemView);

            typeCuisine = (TextView) itemView.findViewById(R.id.type_cuis);
            boutonSup = (TextView) itemView.findViewById(R.id.sup_type_cuis);

            boutonSup.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         deleteType(leType.second);
                     }
                 }
            );
        }

        public void display(Pair<String, Integer> pair) {
            leType = pair;
            typeCuisine.setText(pair.first);
        }

    }
}
