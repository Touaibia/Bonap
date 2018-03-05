package com.bonapp.ujm.myapplication.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bonapp.ujm.myapplication.Model.Client;
import com.bonapp.ujm.myapplication.R;

public class restaurantregister extends AppCompatActivity implements View.OnClickListener {
    Databasehelper  helper = new Databasehelper(this);

    EditText pseudo,email,password,confirmpassword,adress,ville;
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
        switch (view.getId()) {
            case R.id.resgisterRestaurateur:

                pseudo = (EditText) findViewById(R.id.restaurantPseudo);
                password = (EditText) findViewById(R.id.restaurantPswrd);
                confirmpassword = (EditText) findViewById(R.id.restaurantCpswrd);
                email = (EditText) findViewById(R.id.restaurantEmail);
                adress = (EditText) findViewById(R.id.restaurantadress);
                ville = (EditText) findViewById(R.id.restaurantville);


                String userName = pseudo.getText().toString();
                String passWord = password.getText().toString();
                String confirmPassword = confirmpassword.getText().toString();
                String Email = email.getText().toString();
                String Adress = adress.getText().toString();
                String villle = ville.getText().toString();


                if (!passWord.equals(confirmPassword)) {
                    Toast pass = Toast.makeText(restaurantregister.this, " Password don't match", Toast.LENGTH_SHORT);
                    pass.show();
                } else {
                    Client user = new Client();

                    user.setType("restaurant");
                    user.setUsername(userName);
                    user.setEmail(Email);
                    user.setPassword(passWord);
                    user.setAdress(Adress);
                    user.setCity(villle);




                    helper.insertContact(user);
                    Intent i = new Intent(restaurantregister.this, PageRestaurant.class);
                    startActivity(i);
                }
                break;
        }
    }
}
