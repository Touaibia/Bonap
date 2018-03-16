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
import com.bonapp.ujm.myapplication.Model.RepoAdresse;
import com.bonapp.ujm.myapplication.Model.RepoInscription;
import com.bonapp.ujm.myapplication.R;

import static java.lang.Integer.parseInt;


public class clientregister extends AppCompatActivity implements View.OnClickListener {

    Databasehelper helper = new Databasehelper(this);

    EditText username, email, password, confirmpassword, adress, ville, telephone, numero, codePostal, lebeller, ClientPhone;
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
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                username = (EditText) findViewById(R.id.clientPseudo);
                password = (EditText) findViewById(R.id.clientPassword);
                confirmpassword = (EditText) findViewById(R.id.clientConfirmPswrd);
                email = (EditText) findViewById(R.id.clientEmail);
              /*  adress = (EditText) findViewById(R.id.clientadress);
                ville = (EditText) findViewById(R.id.Clienville);
                telephone = (EditText) findViewById(R.id.clientPhone);*/
                numero = (EditText) findViewById(R.id.ClientNumeRue);
                typeVoix = (Spinner) findViewById(R.id.ClientTypeVoix);
                lebeller = (EditText) findViewById(R.id.ClientLebel);
                codePostal = (EditText) findViewById(R.id.ClientCodePostal);
                ClientPhone = (EditText) findViewById(R.id.ClientPhone);

                String userName = username.getText().toString();
                String passWord = password.getText().toString();
                String confirmPassword = confirmpassword.getText().toString();
                String Email = email.getText().toString();
                String typeV = typeVoix.getSelectedItem().toString();
                String NumRue = numero.getText().toString();
                String NomRue = lebeller.getText().toString();
                String CodPostal = codePostal.getText().toString();
                String CleintTel = ClientPhone.getText().toString();
                //  Adresse ClientAdress = new  Adresse( NumRue, "boulvard",NomRue, CodPostal);


                //  String TypeDeVoix = typeVoix.getText()
                if (userName.trim().length() > 8 || userName.trim().length() == 0) {
                    Toast pass = Toast.makeText(clientregister.this, " Username Not valid", Toast.LENGTH_SHORT);
                    pass.show();
                } else {
                    if (!Email.matches(emailPattern)) {
                        Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                        //textView.setText("invalid email");
                    } else {
                        if (passWord.trim().length() > 15 || passWord.trim().length() == 0) {
                            Toast pass = Toast.makeText(clientregister.this, " Paswword Not valid", Toast.LENGTH_SHORT);
                            pass.show();
                        } else {
                            if (!passWord.equals(confirmPassword)) {
                                Toast pass = Toast.makeText(clientregister.this, " Password don't match", Toast.LENGTH_SHORT);
                                pass.show();
                            } else {
                                if (NumRue.trim().length() > 4 || NumRue.trim().length() == 0) {
                                    Toast pass = Toast.makeText(clientregister.this, " Numero De Rue oas valid", Toast.LENGTH_SHORT);
                                    pass.show();

                                } else {
                                    if (NomRue.trim().length() > 40 || NomRue.trim().length() == 0) {
                                        Toast pass = Toast.makeText(clientregister.this, " Nom de la rue non valid", Toast.LENGTH_SHORT);
                                        pass.show();
                                    } else {
                                        if (CodPostal.trim().length() != 4) {
                                            Toast pass = Toast.makeText(clientregister.this, " Code postal non valid", Toast.LENGTH_SHORT);
                                            pass.show();
                                        } else {
                                            if (CleintTel.trim().length() != 10) {
                                                Toast pass = Toast.makeText(clientregister.this, " Numero Telephone non valid rue non valid", Toast.LENGTH_SHORT);
                                                pass.show();
                                            } else {

                                                RepoInscription repoClient = new RepoInscription(this);

                                                RepoAdresse repoAd = new RepoAdresse(this);
                                                Client client = new Client(userName, Email, passWord, CleintTel);


                                                long idClient = repoClient.insertContact(client);

                                                if (idClient != -1) {
                                                    repoAd.ajouter(new Adresse(NumRue, typeV, NomRue, parseInt(CodPostal), idClient));
                                                }
                                                repoClient.close();

                                                Intent i = new Intent(clientregister.this, Accueil.class);
                                                i.putExtra("id_client", idClient);
                                                startActivity(i);

                                            }


/*
                else{
                        Client user = new Client();

                        user.setType("client");
                        user.setUsername(userName);
                        user.setEmail(Email);
                        user.setPassword(passWord);
                      //  user.setAdresse(ClientAdress);
                      user.setAdress(Adress);
                        user.setCity(villle);
                        user.setPhone(Telephone);
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
                */

                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
        }
    }
}

