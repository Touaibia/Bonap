package com.bonapp.ujm.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
        }

    }
}
