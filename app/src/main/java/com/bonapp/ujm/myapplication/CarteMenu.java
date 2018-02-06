package com.bonapp.ujm.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CarteMenu extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carte_menu);
        Restaurant restaurant = new Restaurant();
        //List<Plats> plats = new ArrayList<>();
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

       GridView gridplats = (GridView) findViewById(R.id.gridPlat);
        PlatAdapter adapter = new PlatAdapter(this,restaurant.plats);
        gridplats.setAdapter(adapter);

       GridView gridentree = (GridView) findViewById(R.id.gridEntree);
       EntreeAdapter adapter1 = new EntreeAdapter(this,restaurant.entrees);
        gridentree.setAdapter(adapter1);

       GridView griddess = (GridView) findViewById(R.id.gridDessert);
        DessertAdapter adapter2 = new DessertAdapter(this,restaurant.desserts);
        griddess.setAdapter(adapter2);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.CarteMenu:
                Intent intent = new Intent(this,CarteMenu.class);
                startActivity(intent);
        }
    }
}
