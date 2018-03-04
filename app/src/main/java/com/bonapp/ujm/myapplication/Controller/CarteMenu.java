package com.bonapp.ujm.myapplication.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bonapp.ujm.myapplication.Model.CarteMenuPlatAdapter;
import com.bonapp.ujm.myapplication.Model.Restaurant;
import com.bonapp.ujm.myapplication.R;

public class CarteMenu extends AppCompatActivity implements View.OnClickListener {

    Restaurant restaurant = new Restaurant();

    RecyclerView recyclerView;
    CarteMenuPlatAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carte_menu);

       restaurant.ajoutePlat(R.drawable.planchecharcuterie,"Planche Charcuterie",12,"Description","italien");


        restaurant.ajouteEntree(R.drawable.saladecrevettes,"Salade au gambace",10,"Description","entree");


      restaurant.ajouteDessert(R.drawable.dessert,"Mouche au chocolat",10,"Description","dessert");

        recyclerView = (RecyclerView) findViewById(R.id.gridPlat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TextView entree = (TextView) findViewById(R.id.entree);
        entree.setOnClickListener(this);

        TextView dessert = (TextView) findViewById(R.id.dessert);
        dessert.setOnClickListener(this);
        adapter = new CarteMenuPlatAdapter(this,restaurant.plats);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.plat:
                adapter = new CarteMenuPlatAdapter(this,restaurant.plats);
                recyclerView.setAdapter(adapter);
                break;

            case R.id.entree:
                adapter = new CarteMenuPlatAdapter(this,restaurant.entrees);
                recyclerView.setAdapter(adapter);
                break;

            case R.id.dessert:
                adapter = new CarteMenuPlatAdapter(this,restaurant.desserts);
                recyclerView.setAdapter(adapter);
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.accueil:
                startActivity(new Intent(this, Accueil.class));
                return true;
            case R.id.profile:
                startActivity(new Intent(this, profilclient.class));
                return true;
            case R.id.Reservation:
                startActivity(new Intent(this, MesReservations.class));
                return true;


        }
        return false;
    }
}
