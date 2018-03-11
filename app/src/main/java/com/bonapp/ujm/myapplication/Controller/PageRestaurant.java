package com.bonapp.ujm.myapplication.Controller;

import android.app.DatePickerDialog;
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

import com.bonapp.ujm.myapplication.Controller.CarteMenu;
import com.bonapp.ujm.myapplication.R;

import java.util.Calendar;

public class PageRestaurant extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    int nbp=0;
    DatePickerDialog.OnDateSetListener dateSetListener;
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

                setContentView(R.layout.fait_la_reservation);
                break;
            case R.id.ReservDate:

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year,month,day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month=month+1;
                        String datee = day+"/"+month+"/"+year;
                        textView = (TextView) findViewById(R.id.ReservDate);
                        textView.setText(datee);
                    }
                };

                /*AlertDialog.Builder builder22 = new AlertDialog.Builder(this);
                View view22 = getLayoutInflater().inflate(R.layout.calendrier, null);
                builder22.setTitle(" Selectionnez une date");
                builder22.setView(view22);
                builder22.create().show();*/

                break;
            case R.id.ReservtimeDate:
                AlertDialog.Builder builder23 = new AlertDialog.Builder(this);
                View view23 = getLayoutInflater().inflate(R.layout.calendrier_time, null);
                builder23.setTitle(" Selectionnez l'heure");
                builder23.setView(view23);
                builder23.create().show();
                break;

            case R.id.commande:
                Intent intent1 = new Intent(this,fait_la_commande.class);
                startActivity(intent1);
                break;
            case R.id.ReserveNbpersonne:
                TextView nbpersonne = (TextView) findViewById(R.id.ReserveNbpersonne);
                nbp+=1;
                String s = "Nombre de personnes: "+nbp;
                nbpersonne.setText(s);
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
