package com.bonapp.ujm.myapplication.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bonapp.ujm.myapplication.Model.Adresse;
import com.bonapp.ujm.myapplication.Model.Restaurant;
import com.bonapp.ujm.myapplication.R;

public class restaurantregister extends AppCompatActivity implements View.OnClickListener {
    Databasehelper helper = new Databasehelper(this);

    EditText pseudo, email, password, confirmpassword, numero, codePostal, lebeller, restaurantPhone;
    Spinner typeVoix;
    Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantregister);

        Register = (Button) findViewById(R.id.resgisterRestaurateur);
        Register.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        switch (view.getId()) {
            case R.id.resgisterRestaurateur:

                pseudo = (EditText) findViewById(R.id.restaurantPseudo);
                password = (EditText) findViewById(R.id.restaurantPswrd);
                confirmpassword = (EditText) findViewById(R.id.restaurantCpswrd);
                email = (EditText) findViewById(R.id.restaurantEmail);
                numero = (EditText) findViewById(R.id.restaurantNumeRue);
                typeVoix = (Spinner)findViewById(R.id.restaurantTypeVoix);
                lebeller = (EditText) findViewById(R.id.restaurantLebel);
                codePostal = (EditText) findViewById(R.id.restaurantCodePostal);
                restaurantPhone = (EditText) findViewById(R.id.restaurantPhone);


                String userName = pseudo.getText().toString();
                String passWord = password.getText().toString();
                String confirmPassword = confirmpassword.getText().toString();
                String Email = email.getText().toString();
                String NumRue = numero.getText().toString();
                String NomRue = lebeller.getText().toString();
                String CodPostal = codePostal.getText().toString();
                String resTel = restaurantPhone.getText().toString();
              //  String TypeDeVoix = typeVoix.getText()

                if (userName.trim().length() > 8 || userName.trim().length() == 0) {
                    Toast pass = Toast.makeText(restaurantregister.this, " Username Not valid", Toast.LENGTH_SHORT);
                    pass.show();
                } else {
                    if (!Email.matches(emailPattern)) {
                        Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                        //textView.setText("invalid email");
                    } else {
                        if (passWord.trim().length() > 15 || passWord.trim().length() == 0) {
                            Toast pass = Toast.makeText(restaurantregister.this, " Paswword Not valid", Toast.LENGTH_SHORT);
                            pass.show();
                        } else {
                            if (!passWord.equals(confirmPassword)) {
                                Toast pass = Toast.makeText(restaurantregister.this, " Password don't match", Toast.LENGTH_SHORT);
                                pass.show();
                            } else {
                                if (NumRue.trim().length() > 4 || NumRue.trim().length() == 0) {
                                    Toast pass = Toast.makeText(restaurantregister.this, " Numero De Rue oas valid", Toast.LENGTH_SHORT);
                                    pass.show();

                                } else {
                                    if (NomRue.trim().length() > 40 || NomRue.trim().length() == 0) {
                                        Toast pass = Toast.makeText(restaurantregister.this, " Nom de la rue non valid", Toast.LENGTH_SHORT);
                                        pass.show();
                                    } else {
                                        if (CodPostal.trim().length() != 4) {
                                            Toast pass = Toast.makeText(restaurantregister.this, " Code postal non valid", Toast.LENGTH_SHORT);
                                            pass.show();
                                        } else {
                                            if (resTel.trim().length() != 10) {
                                                Toast pass = Toast.makeText(restaurantregister.this, " Numero Telephone non valid rue non valid", Toast.LENGTH_SHORT);
                                                pass.show();
                                            } else {

                                                Restaurant resto = new Restaurant();
                                                resto.setType("restaurant");
                                                resto.setNom(userName);
                                                resto.setMot_passe(passWord);
                                                resto.setEmail(Email);
                                                resto.setTel(resTel);
                                                resto.setAdresse(new Adresse(NumRue,"type de voix a voir spinner",NomRue,CodPostal));

                                                //helper.insertContact();
                                                Intent i = new Intent(restaurantregister.this, PageRestaurant.class);
                                                startActivity(i);

                                            }

                                        }

                                    }
                                }
                                break;
                            }
                        }
                    }
                }
        }
    }
}
