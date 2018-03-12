package com.bonapp.ujm.myapplication.Controller;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bonapp.ujm.myapplication.Model.Adresse;
import com.bonapp.ujm.myapplication.Model.Client;
import com.bonapp.ujm.myapplication.Model.RepoInscription;
import com.bonapp.ujm.myapplication.R;

import static java.lang.Integer.parseInt;


public class clientregister extends AppCompatActivity implements View.OnClickListener {

     Databasehelper  helper = new Databasehelper(this);

        EditText username,email,password,confirmpassword,adress,ville,telephone,numero, codePostal, lebeller, ClientPhone;
    Spinner typeVoix;
        Button Register;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_clientregister);

            Register = (Button) findViewById(R.id.registerClient);
            Register.setOnClickListener(this);

        }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerClient:

                username = (EditText) findViewById(R.id.clientPseudo);
                password = (EditText) findViewById(R.id.clientPassword);
                confirmpassword = (EditText) findViewById(R.id.clientConfirmPswrd);
                email = (EditText) findViewById(R.id.clientEmail);
              /*  adress = (EditText) findViewById(R.id.clientadress);
                ville = (EditText) findViewById(R.id.Clienville);
                telephone = (EditText) findViewById(R.id.clientPhone);*/
                numero = (EditText) findViewById(R.id.ClientNumeRue);
                typeVoix = (Spinner)findViewById(R.id.ClientTypeVoix);
                lebeller = (EditText) findViewById(R.id.ClientLebel);
                codePostal = (EditText) findViewById(R.id.ClientCodePostal);
                ClientPhone = (EditText) findViewById(R.id.ClientPhone);

                String userName = username.getText().toString();
                String passWord = password.getText().toString();
                String confirmPassword = confirmpassword.getText().toString();
                String Email = email.getText().toString();
              /*  String Adress = adress.getText().toString();
                String villle = ville.getText().toString();
                String Telephone = telephone.getText().toString();*/
                String NumRue = numero.getText().toString();
                String NomRue = lebeller.getText().toString();
                String CodPostal = codePostal.getText().toString();
                String CleintTel = ClientPhone.getText().toString();
                Adresse ClientAdress = new  Adresse( NumRue," ",NomRue,parseInt(CodPostal),0);


                //  String TypeDeVoix = typeVoix.getText()

                if(!passWord.equals(confirmPassword)){
                    Toast pass = Toast.makeText(clientregister.this," Password don't match",Toast.LENGTH_SHORT);
                    pass.show();
                }
                else{
                        Client user = new Client();

                        user.setType("client");
                        user.setUsername(userName);
                        user.setEmail(Email);
                        user.setPassword(passWord);
                        user.setAdresse(ClientAdress);
                    /*    user.setAdress(Adress);
                        user.setCity(villle);
                        user.setPhone(Telephone);*/
                        user.setPhone(CleintTel);
                    RepoInscription repoInscription = new RepoInscription(this);
                    repoInscription.insertContact(user);


                    Intent i = new Intent(this,Accueil.class);
                    Cursor cursor = repoInscription.db.DB.rawQuery("select* from contacts where username= userName",null);
                    while(cursor.moveToNext()){
                        i.putExtra("client", cursor.getString(2));
                    }
                    repoInscription.close();
                    startActivity(i);
                }
                break;
        }
    }
}
