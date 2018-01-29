package com.bonapp.ujm.myapplication;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by maham on 23/01/2018.
 */

public class GestionPublication extends MenuManagerActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        Spinner spinner = (Spinner) findViewById(R.id.selectType);

        spinner.setOnItemSelectedListener(selectItem);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_pub, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        RecyclerView rv = (RecyclerView) findViewById(R.id.list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new PublicationAdapter());
    }

    protected  int getLayoutId(){
        return R.layout.gestion_publications;
    }

    AdapterView.OnItemSelectedListener selectItem = new AdapterView.OnItemSelectedListener() {

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {

        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    };
}
