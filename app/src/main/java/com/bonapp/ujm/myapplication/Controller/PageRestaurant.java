package com.bonapp.ujm.myapplication.Controller;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.bonapp.ujm.myapplication.Controller.CarteMenu;
import com.bonapp.ujm.myapplication.R;

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
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View view2 = getLayoutInflater().inflate(R.layout.fait_la_reservation, null);
                builder.setTitle(" Fait votre reservation");
                builder.setView(view2);
                builder.create().show();
                break;
            case R.id.commande:
                Intent intent1 = new Intent(this,fait_la_commande.class);
                startActivity(intent1);
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
