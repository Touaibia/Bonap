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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_restaurant);
        TextView carteMe =  (TextView) findViewById(R.id.CarteMenu);
        carteMe.setOnClickListener(this);

    }

    public void onClick(View view){

        switch (view.getId()){
            case R.id.CarteMenu:
                Intent intent = new Intent(this,CarteMenu.class);
                startActivity(intent);
                break;
            case R.id.reserve:
                Intent it = getIntent();
                String nom = it.getStringExtra("nom");
                long id = it.getLongExtra("id",0);
                Intent intent1 = new Intent(this,fait_la_reservation.class);
                intent1.putExtra("restau",nom);
                intent1.putExtra("id",id);
                startActivity(intent1);
               // setContentView(R.layout.fait_la_reservation);
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
