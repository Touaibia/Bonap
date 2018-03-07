package com.bonapp.ujm.myapplication.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bonapp.ujm.myapplication.Model.LesResevationsJourAdapter;
import com.bonapp.ujm.myapplication.Model.PublicationAdapter;
import com.bonapp.ujm.myapplication.Model.Reservation;
import com.bonapp.ujm.myapplication.R;

import java.util.ArrayList;

public class GestionEvent extends MenuManagerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        ArrayList<Reservation> lesReserv = new ArrayList<>();

        //Affectation de la liste des publication
        RecyclerView rv = (RecyclerView) findViewById(R.id.les_reserv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new LesResevationsJourAdapter(lesReserv));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gestion_event;
    }

}
