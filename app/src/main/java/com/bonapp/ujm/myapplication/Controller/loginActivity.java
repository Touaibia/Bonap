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
import com.bonapp.ujm.myapplication.R;

public class loginActivity extends AppCompatActivity implements View.OnClickListener{

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
                EditText identifiant = (EditText) findViewById(R.id.logEmail);
                EditText password = (EditText) findViewById(R.id.logPswrd);

                String identif = identifiant.getText().toString();
                String pass = password.getText().toString();

                RepoInscription repoInscription = new RepoInscription(this);
                repoInscription.open();
                Toast.makeText(this, "passw "+pass, Toast.LENGTH_LONG).show();
                long id = repoInscription.identification(identif,pass);
                if(id!=-1){
                    Intent intent = new Intent(this,Accueil.class);
                    intent.putExtra("idclient",id);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "identifiant incorrecte", Toast.LENGTH_LONG).show();

                }

                /*String paswrdClient = helper.searchClient(identif);



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



                }*/
                break;
            case R.id.inscriptionClient_Resto:
                startActivity(new Intent(this, chooseusers.class));


                break;
        }
    }
}
