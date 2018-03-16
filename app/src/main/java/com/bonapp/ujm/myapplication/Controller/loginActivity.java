package com.bonapp.ujm.myapplication.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bonapp.ujm.myapplication.Model.RepoInscription;
import com.bonapp.ujm.myapplication.Model.RepoRestaurant;
import com.bonapp.ujm.myapplication.R;

public class loginActivity extends AppCompatActivity implements View.OnClickListener{
    Boolean identification;
    Databasehelper helper = new Databasehelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView entry;
        setContentView(R.layout.activity_login);
        EditText username, password;
       /* entry = (TextView) findViewById(R.id.link);
        entry.setMovementMethod(LinkMovementMethod.getInstance());
        entry.setOnClickListener( this);*/
       Button log = (Button) findViewById(R.id.log);
         log.setOnClickListener(this);
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
            case R.id.log:
                EditText identifiant = (EditText) findViewById(R.id.logUsername);
                EditText password = (EditText) findViewById(R.id.logPswrd);

                String identif = identifiant.getText().toString();
                String pass = password.getText().toString();

              // String paswrdClient = helper.searchClient(identif);
                RepoRestaurant repoRestau = new RepoRestaurant(this);
                repoRestau.open();

                RepoInscription repoClient = new RepoInscription(this);

                identification = repoRestau.identification(identif,pass);
                if(identification==true){
                    repoRestau.close();
                    Intent i = new Intent(loginActivity.this,GestionPublication.class);
                    startActivity(i);

                }else{
                    identification = repoClient.identification(identif,pass);
                    if(identification==true){
                        repoClient.close();
                        Intent i = new Intent(loginActivity.this,Accueil.class);
                        startActivity(i);
                    }else{
                        Toast temp = Toast.makeText(loginActivity.this," User Not Found",Toast.LENGTH_SHORT);
                        temp.show();
                    }
                }


/*

                if(paswrdClient.equals(pass)){
                    Intent i = new Intent(loginActivity.this,Accueil.class);
                    startActivity(i);
                }
                else{
                    String paswrdrRestaurant = helper.searchrRestaurant(identif);

                    if(paswrdrRestaurant.equals(pass)){

                        Intent i = new Intent(loginActivity.this,GestionPublication.class);
                        startActivity(i);
                    }
                    else{
                        Toast temp = Toast.makeText(loginActivity.this," User Not Found",Toast.LENGTH_SHORT);
                        temp.show();
                    }



                }
                */
                break;
            case R.id.inscriptionClient_Resto:
                startActivity(new Intent(this, chooseusers.class));


                break;
        }
    }
}
