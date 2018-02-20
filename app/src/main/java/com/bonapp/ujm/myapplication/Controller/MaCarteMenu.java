package com.bonapp.ujm.myapplication.Controller;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
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

    AlertDialog.Builder builder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        builder = new AlertDialog.Builder(this);

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

        adapter = new GestionPlatAdapter(this,restaurant.plats, builder);
        recyclerView.setAdapter(adapter);


    }


    public void onClick(View view) {
        switch (view.getId()){
            case R.id.plat:
                adapter = new GestionPlatAdapter(this,restaurant.plats, builder);
                recyclerView.setAdapter(adapter);
                break;

            case R.id.entree:
                adapter = new GestionPlatAdapter(this,restaurant.entrees, builder);
                recyclerView.setAdapter(adapter);
                break;

            case R.id.dessert:
                adapter = new GestionPlatAdapter(this,restaurant.desserts, builder);
                recyclerView.setAdapter(adapter);
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ma_carte_menu;
    }

    //la methode qui recupere l'image selectionnée
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == RESULT_OK && requestCode == 20){
            Uri imageUri = data.getData();
            //set the image in an ImageView
            adapter.setUri(imageUri);
        }
    }
}
