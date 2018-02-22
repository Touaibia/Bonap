package com.bonapp.ujm.myapplication.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_);
        Intent intent = new Intent(MainActivity.this, loginActivity.class);
        startActivity(intent);

    }
    public void onClick(View view) {

    }
}
