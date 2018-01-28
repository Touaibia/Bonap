package com.bonapp.ujm.myapplication;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by maham on 23/01/2018.
 */

public class GestionPublication extends MenuManagerActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        RecyclerView rv = (RecyclerView) findViewById(R.id.list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new PublicationAdapter());
    }

    protected  int getLayoutId(){
        return R.layout.gestion_publications;
    }
}
