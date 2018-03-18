package com.bonapp.ujm.myapplication.Controller;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bonapp.ujm.myapplication.Model.Client;
import com.bonapp.ujm.myapplication.Model.LesResevationsJourAdapter;
import com.bonapp.ujm.myapplication.Model.RepoInscription;
import com.bonapp.ujm.myapplication.Model.RepoReservation;
import com.bonapp.ujm.myapplication.Model.Reservation;
import com.bonapp.ujm.myapplication.R;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GestionEvent extends MenuManagerActivity {

    RecyclerView rv;
    LesResevationsJourAdapter adapter;
    SimpleDateFormat sdf;
    String jour;
    long time;
    long id_restau;
    int service;
    ArrayList<Reservation> lesReserv;
    ArrayList<Reservation> lesReservMidi;
    ArrayList<Reservation> lesReservSoir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        final TextView today = (TextView) findViewById(R.id.today);
        TextView avant = (TextView) findViewById(R.id.go_left);
        TextView apres = (TextView) findViewById(R.id.go_right);

        //Affectation de la liste des reservations
        rv = (RecyclerView) findViewById(R.id.les_reserv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        //rv.setAdapter(new LesResevationsJourAdapter(this,lesReserv));

        service = 0;

        sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        jour = sdf.format(date);

        today.setText(jour);

        id_restau=1;

//        RepoInscription repoInscription = new RepoInscription(this);
//        repoInscription.open();
//        Client cl = new Client("MNour7","nour@gmail.com","nour","0669965325");
//        long id_cl = repoInscription.insertContact(cl);
//        repoInscription.close();
//
//        RepoReservation repoReservation = new RepoReservation(this);
//        repoReservation.open();
//
//        Reservation res = new Reservation( 5,0,date,"11:30",id_restau,id_cl);
//        Reservation res1 = new Reservation( 10,0,date,"12:00",id_restau,id_cl);
//        Reservation res2 = new Reservation( 3,0,date,"13:00",id_restau,id_cl);
//        Reservation res3 = new Reservation( 5,1,date,"20:30",id_restau,id_cl);
//        Reservation res4 = new Reservation( 2,1,date,"19:30",id_restau,id_cl);
//
//        repoReservation.ajouter(res);
//        repoReservation.ajouter(res1);
//        repoReservation.ajouter(res2);
//        repoReservation.ajouter(res3);
//        repoReservation.ajouter(res4);
//
//        repoReservation.close();

        setReservation();

        avant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    jour = jourAvant(jour);
                    today.setText(jour);
                    setReservation();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        apres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    jour = jourApres(jour);
                    today.setText(jour);
                    setReservation();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gestion_event;
    }

    public void setReservation(){
        RepoReservation repoReservation = new RepoReservation(GestionEvent.this);
        repoReservation.open();
        lesReserv = repoReservation.selectionRestau(id_restau,jour);
        Log.d("Le Nombre de Reserv", ""+lesReserv.size());
        lesReservMidi = new ArrayList<>();
        lesReservSoir = new ArrayList<>();


        for (int i= 0; i < lesReserv.size(); i++){
            if (lesReserv.get(i).getService() == 0)
                lesReservMidi.add(lesReserv.get(i));
            else
                lesReservSoir.add(lesReserv.get(i));
        }

        Log.d("Le Reserv Midi", ""+lesReservMidi.size());
        Log.d("Le Reserv Soir", ""+lesReservSoir.size());

        if (service == 0){
            adapter = new LesResevationsJourAdapter(GestionEvent.this, lesReservMidi);
            rv.setAdapter(adapter);
        }
        else{
            adapter = new LesResevationsJourAdapter(GestionEvent.this, lesReservSoir);
            rv.setAdapter(adapter);
        }
    }

    public void radioChange(View view){
        switch (view.getId()){
            case R.id.serv_midi:
                adapter = new LesResevationsJourAdapter(GestionEvent.this, lesReservMidi);
                rv.setAdapter(adapter);
                service = 0;
                Toast.makeText(GestionEvent.this,
                        "Changement de Service = "+service,
                        Toast.LENGTH_LONG ).show();
                break;
            case R.id.serv_soir:
                adapter = new LesResevationsJourAdapter(GestionEvent.this, lesReservSoir);
                rv.setAdapter(adapter);
                service = 1;
                Toast.makeText(GestionEvent.this,
                        "Changement de Service = "+service,
                        Toast.LENGTH_LONG ).show();
                break;
        }
    }

    public String jourAvant(String jour) throws ParseException {
        Date date = sdf.parse(jour);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -1);

        return  sdf.format(c.getTime());
    }

    public String jourApres(String jour) throws ParseException {
        Date date = sdf.parse(jour);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);

        return  sdf.format(c.getTime());
    }
}
