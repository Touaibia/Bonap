package com.bonapp.ujm.myapplication.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bonapp.ujm.myapplication.R;


public class clientregister extends AppCompatActivity implements View.OnClickListener {

     Databasehelper  helper = new Databasehelper(this);

        EditText username,email,password,confirmpassword,adress,ville,telephone;
        Button Register;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_clientregister);

            Register = (Button) findViewById(R.id.clientRegister);
            Register.setOnClickListener(this);

        }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clientRegister:

                username = (EditText) findViewById(R.id.clientPseudo);
                password = (EditText) findViewById(R.id.clientPassword);
                confirmpassword = (EditText) findViewById(R.id.clientConfirmPswrd);
                email = (EditText) findViewById(R.id.clientEmail);
                adress = (EditText) findViewById(R.id.clientadress);
                ville = (EditText) findViewById(R.id.Clienville);
                telephone = (EditText) findViewById(R.id.clientPhone);

                String userName = username.getText().toString();
                String passWord = password.getText().toString();
                String confirmPassword = confirmpassword.getText().toString();
                String Email = email.getText().toString();
                String Adress = adress.getText().toString();
                String villle = ville.getText().toString();
                String Telephone = telephone.getText().toString();

                if(!passWord.equals(confirmPassword)){
                    Toast pass = Toast.makeText(clientregister.this," Password don't match",Toast.LENGTH_SHORT);
                    pass.show();
                }
                else{
                        Contact user = new Contact();

                        user.setType("client");
                        user.setUsername(userName);
                        user.setEmail(Email);
                        user.setPassword(passWord);
                        user.setAdress(Adress);
                        user.setCity(villle);
                        user.setPhone(Telephone);

                        helper.insertContact(user);
                    Intent i = new Intent(clientregister.this,Accueil.class);
                    startActivity(i);
                }
                break;
        }
    }
}