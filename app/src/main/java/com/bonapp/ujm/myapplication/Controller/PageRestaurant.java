package com.bonapp.ujm.myapplication.Controller;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bonapp.ujm.myapplication.Controller.CarteMenu;
import com.bonapp.ujm.myapplication.R;

import java.util.Calendar;
import java.util.Timer;

public class PageRestaurant extends AppCompatActivity implements View.OnClickListener{

long idclient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_restaurant);
        TextView carteMe =  (TextView) findViewById(R.id.CarteMenu);
        carteMe.setOnClickListener(this);

        Intent intent = getIntent();
        String nomrestau = intent.getStringExtra("nomRestau");
        long id = intent.getLongExtra("id",-1);
        long idcc = intent.getLongExtra("idclient",-1);
        if(idcc!=-1) idclient = idcc;

        TextView nomRestau = (TextView) findViewById(R.id.pageNomRestau);
        nomRestau.setText(nomrestau);

    }

    public void onClick(View view){

        switch (view.getId()){
            case R.id.CarteMenu:
                Intent intent = getIntent();
                String nomRe = intent.getStringExtra("nom");
                long idRe = intent.getLongExtra("id",-1);
                Intent intentR = new Intent(this,CarteMenu.class);
                if(idRe!=-1) intentR.putExtra("idrestau",idRe);
                startActivity(intentR);
                break;
            case R.id.reserve:
                Intent it = getIntent();
                String nom = it.getStringExtra("nomRestau");
                long id = it.getLongExtra("idRestau",-1);
                long idc = it.getLongExtra("idclient",-1);
                Intent intent1 = new Intent(this,fait_la_reservation.class);
                intent1.putExtra("restau",nom);
                intent1.putExtra("idrestau",id);
                intent1.putExtra("idclient",idc);
                startActivity(intent1);

                break;

            case R.id.commande:
                Intent intent2 = new Intent(this,fait_la_commande.class);
                startActivity(intent2);
                break;
            case R.id.ReserveNbpersonne:

                break;


            case R.id.ajouteCommentaire:
                AlertDialog.Builder buil = new AlertDialog.Builder(this);
                View view1 = getLayoutInflater().inflate(R.layout.ajoutecommentaire,null);
                //buil.setTitle("Ajoute d'un nouveau commentaire");
                buil.setView(view1);
                buil.create().show();

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
