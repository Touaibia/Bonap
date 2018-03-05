package com.bonapp.ujm.myapplication.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bonapp.ujm.myapplication.Model.CommandePlatAdapter;
import com.bonapp.ujm.myapplication.Model.Restaurant;
import com.bonapp.ujm.myapplication.R;

public class fait_la_commande extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fait_la_commande);
        Restaurant restaurant = new Restaurant();

        /*restaurant.ajoutePlat("planchecharcuterie",R.drawable.planchecharcuterie);
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

       CommandePlatAdapter adapter1 = new CommandePlatAdapter(this,restaurant.plats);
        RecyclerView listtv = (RecyclerView) findViewById(R.id.commandeGridPlat);
        listtv.setLayoutManager(new LinearLayoutManager(this));
        listtv.setAdapter(adapter1);

        GridView gridView11 = (GridView) findViewById(R.id.commandeGridEntree);
        CommandePlatAdapter adapter11 = new CommandePlatAdapter(this,restaurant.entrees);
        gridView11.setAdapter(adapter11);

        GridView gridView111 = (GridView) findViewById(R.id.commandeGridDessert);
        CommandePlatAdapter adapter111 = new CommandePlatAdapter(this,restaurant.desserts);
        gridView111.setAdapter(adapter111);*/
    }
    public void onClick(View view){

    }
}
