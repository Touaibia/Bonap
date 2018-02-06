package com.bonapp.ujm.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class chooseusers extends AppCompatActivity implements View.OnClickListener {
    ImageButton restaurateur, client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_chooseusers);

        restaurateur = (ImageButton)findViewById(R.id.imageButtonRestaurateur);
        client = (ImageButton)findViewById(R.id.imageButtonClient);

        restaurateur.setOnClickListener(this);
        client.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButtonClient:
                startActivity(new Intent(this, clientregister.class));

                break;
            case R.id.imageButtonRestaurateur:
                startActivity(new Intent(this, restaurantregister.class));
                break;
        }

    }
}