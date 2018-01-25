package com.bonapp.ujm.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    }
    public void onClick(View v) {
        switch (v.getId()) {
            //case R.id.link:
                //startActivity(new Intent(this, MainActivity.class));
               // break;
            case R.id.login:
                startActivity(new Intent(this, Accueil.class));
                //startActivity(new Intent(this, MainActivity.class));
        }
    }
}
