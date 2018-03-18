package com.bonapp.ujm.myapplication.Controller;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bonapp.ujm.myapplication.Model.LesResevationsJourAdapter;
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
        rv.setAdapter(new LesResevationsJourAdapter(this,lesReserv));

        service = 1;

        sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        jour = sdf.format(date);

        today.setText(jour);

        try {
            time = sdf.parse(jour).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        id_restau=1;

        setReservation();

        avant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    jour = jourAvant(jour);
                    today.setText(jour);
                    time = sdf.parse(jour).getTime();
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
                    time = sdf.parse(jour).getTime();
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
        lesReserv = repoReservation.selectionRestau((int)id_restau,new Date(time));
        lesReservMidi = new ArrayList<>();
        lesReservSoir = new ArrayList<>();

        for (int i= 0; i < lesReserv.size(); i++){
            if (lesReserv.get(i).getService() == 0)
                lesReservMidi.add(lesReserv.get(i));
            else
                lesReservSoir.add(lesReserv.get(i));
        }

        if (service == 1){
            adapter = new LesResevationsJourAdapter(GestionEvent.this, lesReservMidi);
            rv.setAdapter(adapter);
        }
        else{
            adapter = new LesResevationsJourAdapter(GestionEvent.this, lesReservMidi);
            rv.setAdapter(adapter);
            service = 1;
        }
    }

    public void radioChange(View view){
        switch (view.getId()){
            case R.id.serv_midi:
                adapter = new LesResevationsJourAdapter(GestionEvent.this, lesReservMidi);
                rv.setAdapter(adapter);
                service = 1;
                break;
            case R.id.serv_soir:
                adapter = new LesResevationsJourAdapter(GestionEvent.this, lesReservSoir);
                rv.setAdapter(adapter);
                service = 2;
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
