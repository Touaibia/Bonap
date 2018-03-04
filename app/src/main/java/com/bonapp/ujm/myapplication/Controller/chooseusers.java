package com.bonapp.ujm.myapplication.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bonapp.ujm.myapplication.R;

public class chooseusers extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooseusers);

        Button b1 = (Button) findViewById(R.id.inscriptionClient);
        Button b2 = (Button) findViewById(R.id.inscriptionResto);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

    }

    public void onClick(View view){

        switch(view.getId()){

            case R.id.inscriptionClient:
                startActivity(new Intent(this,clientregister.class));
                break;
            case R.id.inscriptionResto:
                startActivity(new Intent(this,restaurantregister.class));
                break;

        }
    }
}
