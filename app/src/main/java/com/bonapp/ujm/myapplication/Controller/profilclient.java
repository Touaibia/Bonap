package com.bonapp.ujm.myapplication.Controller;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bonapp.ujm.myapplication.Model.Adresse;
import com.bonapp.ujm.myapplication.Model.Client;
import com.bonapp.ujm.myapplication.Model.RepoAdresse;
import com.bonapp.ujm.myapplication.Model.RepoAdresseClient;
import com.bonapp.ujm.myapplication.Model.RepoInscription;
import com.bonapp.ujm.myapplication.Model.SharedPrefManager;
import com.bonapp.ujm.myapplication.R;

public class profilclient extends AppCompatActivity implements View.OnClickListener{
    long idclient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilclient);
        Intent intent = getIntent();
        long idcc = intent.getLongExtra("idclient",-1);
        if(idcc!=-1) idclient = idcc;

        TextView nom = (TextView) findViewById(R.id.clientPseudoModif);
        TextView email = (TextView) findViewById(R.id.clientEmailModif);
        TextView telep = (TextView) findViewById(R.id.clientEmailModif);
        TextView adres = (TextView) findViewById(R.id.clientAdressModif);

        RepoInscription repo = new RepoInscription(this);
        repo.open();
        Client c = repo.getClient(idclient);
        nom.setText(c.getUsername());
        email.setText(c.getEmail());
        telep.setText(c.getTelephone());
        repo.close();
        RepoAdresseClient adresse = new RepoAdresseClient(this);
        adresse.open();
        Adresse ad = adresse.selectionner(idclient);
        adres.setText(ad.getNumero()+" "+ad.getType_voie()+" "+ad.getIntitule()+" "+ad.getCode_postal());
        adresse.close();

    }

    @Override
    public void onClick(View view) {

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
            case R.id.ClientLogOut:
                SharedPrefManager.getInstance(this).LogOut();
                finish();
                startActivity(new Intent(this,loginActivity.class));
                break;



        }

        return super.onOptionsItemSelected(item);
    }

}
