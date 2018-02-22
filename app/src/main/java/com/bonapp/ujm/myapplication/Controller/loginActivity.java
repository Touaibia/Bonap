package com.bonapp.ujm.myapplication.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bonapp.ujm.myapplication.R;

public class loginActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView entry;
        setContentView(R.layout.activity_login);
       /* entry = (TextView) findViewById(R.id.link);
        entry.setMovementMethod(LinkMovementMethod.getInstance());
        entry.setOnClickListener( this);*/
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        Button login1 = (Button) findViewById(R.id.login1);
        login1.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login1:
                startActivity(new Intent(this, GestionPublication.class));
                break;
            case R.id.login:
                startActivity(new Intent(this, Accueil.class));
                break;
        }
    }
}
