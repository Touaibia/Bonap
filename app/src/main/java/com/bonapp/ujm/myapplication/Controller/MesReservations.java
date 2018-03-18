package com.bonapp.ujm.myapplication.Controller;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bonapp.ujm.myapplication.Model.RepoReservation;
import com.bonapp.ujm.myapplication.Model.Reservation;
import com.bonapp.ujm.myapplication.Model.ReservationAdapter;
import com.bonapp.ujm.myapplication.Model.Restaurant;
import com.bonapp.ujm.myapplication.Model.SuggestionRestoAdapter;
import com.bonapp.ujm.myapplication.R;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MesReservations extends AppCompatActivity {
    long idclient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_reservations);

        List<Reservation> list = new ArrayList<>();
        RepoReservation reservation = new RepoReservation(this);
        reservation.open();
        Intent intent = getIntent();
        long idc = intent.getLongExtra("idclient",-1);

        try {
            if(idc!=-1) {
                list = reservation.selectionClient((int)idc);
                idclient = idc;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Toast.makeText(this,"nombre reserver "+list.size(),Toast.LENGTH_LONG).show();

        RecyclerView listv = (RecyclerView) findViewById(R.id.listReservation);

        listv.setLayoutManager(new LinearLayoutManager(this));
        listv.setAdapter(new ReservationAdapter(list,this));



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
                Intent intent0 = new Intent(this, Accueil.class);
                intent0.putExtra("idclient",idclient);
                startActivity(intent0);
                return true;
            case R.id.profile:
                Intent intent11 = new Intent(this, profilclient.class);
                intent11.putExtra("idclient",idclient);
                startActivity(intent11);
                return true;
            case R.id.Reservation:
                Intent intentm = new Intent(this, MesReservations.class);
                intentm.putExtra("idclient",idclient);
                startActivity(intentm);
                return true;
            case R.id.Reglage:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View view2 = getLayoutInflater().inflate(R.layout.parametre, null);
                builder.setTitle("Parametre");
                TextView pseudo = view2.findViewById(R.id.pseudo);
                TextView email = view2.findViewById(R.id.clientemail);
                Intent intent = getIntent();
                // Client client = (Client) intent.getSerializableExtra("client");
                String us = intent.getStringExtra("client");
                pseudo.setText(us);
                //pseudo.setText(us);
                // email.setText(client.getEmail());
                builder.setView(view2);

                builder.create().show();
                return true;


        }
        return false;
    }
}

