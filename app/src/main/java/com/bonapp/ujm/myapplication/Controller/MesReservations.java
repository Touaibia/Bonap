package com.bonapp.ujm.myapplication.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.bonapp.ujm.myapplication.Model.Reservation;
import com.bonapp.ujm.myapplication.Model.ReservationAdapter;
import com.bonapp.ujm.myapplication.Model.Restaurant;
import com.bonapp.ujm.myapplication.Model.SuggestionRestoAdapter;
import com.bonapp.ujm.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MesReservations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_reservations);

        List<Reservation> list = new ArrayList<>();

        Reservation r1 = new Reservation(new Restaurant("R2", R.drawable.icons8menuutilisateurhomme50), "12h-13h");
        Reservation r2 = new Reservation(new Restaurant("R2", R.drawable.icons8menuutilisateurhomme50), "14h-14h30");
        Reservation r3 = new Reservation(new Restaurant("R2", R.drawable.icons8menuutilisateurhomme50), "12h-13h30");
        Reservation r4 = new Reservation(new Restaurant("R2", R.drawable.icons8menuutilisateurhomme50), "20h-21h");

        list.add(r1);
        list.add(r2);
        list.add(r3);
        list.add(r4);

        RecyclerView listv = (RecyclerView) findViewById(R.id.listReservation);

        listv.setLayoutManager(new LinearLayoutManager(this));
        listv.setAdapter(new ReservationAdapter(list));


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

