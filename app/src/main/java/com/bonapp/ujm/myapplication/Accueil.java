package com.bonapp.ujm.myapplication;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.app.PendingIntent.getActivity;

public class Accueil extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        Restaurant R1 = new Restaurant("R1",R.drawable.common_full_open_on_phone);
        Restaurant R2 = new Restaurant("R2",R.drawable.icons8menuutilisateurhomme50);
        Restaurant R3 = new Restaurant("R3",R.drawable.common_google_signin_btn_icon_dark_normal);
        Restaurant R4 = new Restaurant("R4",R.drawable.icons8fork50);
        List<Restaurant> list = new ArrayList<Restaurant>();
        list.add(R1);
        list.add(R2);
        list.add(R3);
        list.add(R4);


        RecyclerView listv = (RecyclerView) findViewById(R.id.list);

        listv.setLayoutManager(new LinearLayoutManager(this));
        listv.setAdapter(new SuggestionRestoAdapter(list));
        TextView rech = (TextView) findViewById(R.id.filtreRech);
        rech.setOnClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.filtreRech:
                AlertDialog.Builder builder = new AlertDialog.Builder(Accueil.this);
                builder.create();
                break;
        }
    }
}
