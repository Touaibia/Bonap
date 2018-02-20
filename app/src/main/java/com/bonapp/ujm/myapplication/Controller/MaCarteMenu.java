package com.bonapp.ujm.myapplication.Controller;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bonapp.ujm.myapplication.Model.GestionPlatAdapter;
import com.bonapp.ujm.myapplication.Model.Restaurant;
import com.bonapp.ujm.myapplication.R;

public class MaCarteMenu extends MenuManagerActivity {

    Restaurant restaurant = new Restaurant();

    RecyclerView recyclerView;

    GestionPlatAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        restaurant.ajoutePlat("planchecharcuterie",R.drawable.planchecharcuterie);
        restaurant.ajoutePlat("planchecharcuterie",R.drawable.planchecharcuterie);
        restaurant.ajoutePlat("planchecharcuterie",R.drawable.planchecharcuterie);
        restaurant.ajoutePlat("planchecharcuterie",R.drawable.planchecharcuterie);
        restaurant.ajoutePlat("planchecharcuterie",R.drawable.planchecharcuterie);

        restaurant.ajouteEntree("salade gambas",R.drawable.saladecrevettes);
        restaurant.ajouteEntree("salade gambas",R.drawable.saladecrevettes);
        restaurant.ajouteEntree("salade gambas",R.drawable.saladecrevettes);
        restaurant.ajouteEntree("salade gambas",R.drawable.saladecrevettes);

        restaurant.ajouteDessert("mouche Chololat",R.drawable.dessert);
        restaurant.ajouteDessert("mouche Chololat",R.drawable.dessert);
        restaurant.ajouteDessert("mouche Chololat",R.drawable.dessert);
        restaurant.ajouteDessert("mouche Chololat",R.drawable.dessert);


        recyclerView = (RecyclerView) findViewById(R.id.gridPlat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new GestionPlatAdapter(this,restaurant.plats);
        recyclerView.setAdapter(adapter);


    }


    public void onClick(View view) {
        switch (view.getId()){
            case R.id.plat:
                adapter = new GestionPlatAdapter(this,restaurant.plats);
                recyclerView.setAdapter(adapter);
                break;

            case R.id.entree:
                adapter = new GestionPlatAdapter(this,restaurant.entrees);
                recyclerView.setAdapter(adapter);
                break;

            case R.id.dessert:
                adapter = new GestionPlatAdapter(this,restaurant.desserts);
                recyclerView.setAdapter(adapter);
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ma_carte_menu;
    }
}
