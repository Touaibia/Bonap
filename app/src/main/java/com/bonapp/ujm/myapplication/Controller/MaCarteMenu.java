package com.bonapp.ujm.myapplication.Controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bonapp.ujm.myapplication.Model.GestionPlatAdapter;
import com.bonapp.ujm.myapplication.Model.Restaurant;
import com.bonapp.ujm.myapplication.R;

public class MaCarteMenu extends MenuManagerActivity {

    Restaurant restaurant = new Restaurant();

    RecyclerView recyclerView;

    GestionPlatAdapter adapter;

    ImageView conteneur;

    AlertDialog.Builder builder;
    int onglet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        builder = new AlertDialog.Builder(this);

        restaurant.ajoutePlat("planchecharcuterie",R.drawable.planchecharcuterie);
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


        recyclerView = (RecyclerView) findViewById(R.id.gridPlat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new GestionPlatAdapter(this,restaurant.plats, builder);
        recyclerView.setAdapter(adapter);

        onglet = 1;

        Button ajout_nouv = (Button) findViewById(R.id.ajout_nouv_plat);
        ajout_nouv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ajoutNouv();
            }
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ma_carte_menu;
    }


    public void onClick(View view) {
        switch (view.getId()){
            case R.id.plat:
                adapter = new GestionPlatAdapter(this,restaurant.plats, builder);
                recyclerView.setAdapter(adapter);
                onglet = 1;
                break;

            case R.id.entree:
                adapter = new GestionPlatAdapter(this,restaurant.entrees, builder);
                recyclerView.setAdapter(adapter);
                onglet = 2;
                break;

            case R.id.dessert:
                adapter = new GestionPlatAdapter(this,restaurant.desserts, builder);
                recyclerView.setAdapter(adapter);
                onglet = 3;
                break;
        }
    }


    public  void ajoutNouv(){
        View myView;
        switch (onglet){
            case 1:
                myView = getLayoutInflater().inflate(R.layout.nouv_plat,null);
                conteneur = myView.findViewById(R.id.img_nouv_plat);
                builder.setTitle("Ajout d'un nouveau Plat")
                        .setView(myView)
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //ici on traite le formulaire et ajout en BD dans les plats

                                Toast.makeText(MaCarteMenu.this,
                                        "Les Modificatons sont prises en compte est enregistrée en BD !",
                                        Toast.LENGTH_LONG ).show();

                            }
                        })
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder.show();
                break;
            case 2:
                myView = getLayoutInflater().inflate(R.layout.nouv_plat,null);
                builder.setTitle("Ajout d'un nouvelle entrée")
                        .setView(myView)
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //ici on traite le formulaire et ajout en BD dans les entrées

                                Toast.makeText(MaCarteMenu.this,
                                        "Les Modificatons sont prises en compte est enregistrée en BD !",
                                        Toast.LENGTH_LONG ).show();

                            }
                        })
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder.show();
                break;
            case 3:
                myView = getLayoutInflater().inflate(R.layout.nouv_plat,null);
                builder.setTitle("Ajout d'un nouveau Dessert")
                        .setView(myView)
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //ici on traite le formulaire et ajout en BD dans les Desserts

                                Toast.makeText(MaCarteMenu.this,
                                        "Les Modificatons sont prises en compte est enregistrée en BD !",
                                        Toast.LENGTH_LONG ).show();

                            }
                        })
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder.show();
                break;
        }

    }

    //La methode pour selectionner une image
    public void selectImageFromGalery(View v){
        Intent photoPicker = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(photoPicker,30);
    }



    //la methode qui recupere l'image selectionnée
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == RESULT_OK && requestCode == 20){
            Uri imageUri = data.getData();
            //set the image in an ImageView
            adapter.setUri(imageUri);
        }
        else if (resultCode == RESULT_OK && requestCode == 30){
            Uri imageUri = data.getData();
            //set the image in an ImageView
            conteneur.setImageURI(imageUri);
        }
    }
}
