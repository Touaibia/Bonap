package com.bonapp.ujm.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class loginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView entry;
        setContentView(R.layout.activity_login);
       /* entry = (TextView) findViewById(R.id.link);
        entry.setMovementMethod(LinkMovementMethod.getInstance());
        entry.setOnClickListener( this);*/
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Log.i("Bouton :","Client");
                startActivity(new Intent(loginActivity.this, GestionProfil.class));
            }
        });

        Button resto = (Button) findViewById(R.id.login1);
        resto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Log.i("Bouton :","Restaurant");
                startActivity(new Intent(loginActivity.this, GestionPublication.class));
            }
        });
    }


}
