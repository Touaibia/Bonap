package com.bonapp.ujm.myapplication.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bonapp.ujm.myapplication.Model.PublicationAdapter;
import com.bonapp.ujm.myapplication.Model.Restaurant;
import com.bonapp.ujm.myapplication.R;

/**
 * Created by maham on 23/01/2018.
 */

public class GestionPublication extends MenuManagerActivity implements AdapterView.OnItemSelectedListener {

    AlertDialog.Builder builder;

    ImageView conteneur;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        Spinner spinner = (Spinner) findViewById(R.id.selectType);

        spinner.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_pub, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //Affectation de la liste des publication
        RecyclerView rv = (RecyclerView) findViewById(R.id.list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new PublicationAdapter());

        //Creation d'une AlertDialog Generale
        builder = new AlertDialog.Builder(this);
    }

    protected  int getLayoutId(){
        return R.layout.gestion_publications;
    }


    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        //on crée un layout pour l'AlertDialog
        View myView;
        String elem = parent.getItemAtPosition(pos).toString();
        switch (elem){
            case "Publication Simple":
                //publication Simple
                myView = getLayoutInflater().inflate(R.layout.pub_simple,null);

                //On recupere l'ImageView du layout
                conteneur = (ImageView) myView.findViewById(R.id.imgPic);

                builder.setTitle("Publication Simple")
                        .setView(myView)
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // ici on traite le formulaire
                                Toast.makeText(GestionPublication.this,
                                        "La publication est enregistrée en BD !",Toast.LENGTH_LONG ).show();
                            }
                        })
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder.show();
                break;
            case "Menu du Jour":
                //Menu du jour
                myView = getLayoutInflater().inflate(R.layout.menu_du_jour,null);

                //On recupere l'ImageView du layout
                conteneur = (ImageView) myView.findViewById(R.id.imgPic);

                builder.setTitle("Publication d'un Menu du Jour")
                        .setView(myView)
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // ici on traite le formulaire
                                Toast.makeText(GestionPublication.this,
                                        "La publication est enregistrée en BD !",Toast.LENGTH_LONG ).show();
                            }
                        })
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder.show();
                break;
            case "Annonce Promotionnelle":
                //Annonce Promotionnelle
                myView = getLayoutInflater().inflate(R.layout.annonce_promo,null);

                //on charge la liste dans le spinner
                Spinner spinner = myView.findViewById(R.id.selectMenus);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(GestionPublication.this,
                        R.array.list_menu, android.R.layout.simple_spinner_item);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                spinner.setAdapter(adapter);

                //On recupere l'ImageView du layout
                conteneur = (ImageView) myView.findViewById(R.id.imgPic);

                builder.setTitle("Publication d'un Menu du Jour")
                        .setView(myView)
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // ici on traite le formulaire
                                Toast.makeText(GestionPublication.this,
                                        "La publication est enregistrée en BD !",Toast.LENGTH_LONG ).show();
                            }
                        })
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder.show();
                break;
            case "Annonce d'événement":
                //Annonce Evenementielle
                break;

        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    //La methode pour selectionner une image
    public void selectImageFromGalery(View v){
        Intent photoPicker = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(photoPicker,20);
    }

    //la methode qui recupere l'image selectionnée
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == RESULT_OK && requestCode == 20){
            Uri imageUri = data.getData();
            //set the image in an ImageView
            conteneur.setImageURI(imageUri);
        }
    }

    public static class fait_la_commande extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fait_la_commande);
            Restaurant restaurant = new Restaurant();

            /*restaurant.ajoutePlat("planchecharcuterie",R.drawable.planchecharcuterie);
            restaurant.ajoutePlat("planchecharcuterie",R.drawable.planchecharcuterie);
            restaurant.ajoutePlat("planchecharcuterie",R.drawable.planchecharcuterie);
            restaurant.ajoutePlat("planchecharcuterie",R.drawable.planchecharcuterie);
            restaurant.ajoutePlat("planchecharcuterie",R.drawable.planchecharcuterie);

            restaurant.ajouteEntree("salade gambas",R.drawable.saladecrevettes);
            restaurant.ajouteEntree("salade gambas",R.drawable.saladecrevettes);
            restaurant.ajouteEntree("salade gambas",R.drawable.saladecrevettes);
            restaurant.ajouteEntree("salade gambas",R.drawable.saladecrevettes);

            restaurant.ajouteDessert("mouche Chololat",R.drawable.dessert);
            restaurant.ajouteDessert("mouche Chololat",R.drawable.dessert);
            restaurant.ajouteDessert("mouche Chololat",R.drawable.dessert);
            restaurant.ajouteDessert("mouche Chololat",R.drawable.dessert);

           CommandePlatAdapter adapter1 = new CommandePlatAdapter(this,restaurant.plats);
            RecyclerView listtv = (RecyclerView) findViewById(R.id.commandeGridPlat);
            listtv.setLayoutManager(new LinearLayoutManager(this));
            listtv.setAdapter(adapter1);

            GridView gridView11 = (GridView) findViewById(R.id.commandeGridEntree);
            CommandePlatAdapter adapter11 = new CommandePlatAdapter(this,restaurant.entrees);
            gridView11.setAdapter(adapter11);

            GridView gridView111 = (GridView) findViewById(R.id.commandeGridDessert);
            CommandePlatAdapter adapter111 = new CommandePlatAdapter(this,restaurant.desserts);
            gridView111.setAdapter(adapter111);*/
        }
        public void onClick(View view){

        }
    }
}
