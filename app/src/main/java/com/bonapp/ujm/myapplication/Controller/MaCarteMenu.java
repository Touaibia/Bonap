package com.bonapp.ujm.myapplication.Controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bonapp.ujm.myapplication.Model.GestionPlatAdapter;
import com.bonapp.ujm.myapplication.Model.Image;
import com.bonapp.ujm.myapplication.Model.Plat;
import com.bonapp.ujm.myapplication.Model.RepoImage;
import com.bonapp.ujm.myapplication.Model.RepoPlat;
import com.bonapp.ujm.myapplication.Model.RepoRestaurant;
import com.bonapp.ujm.myapplication.Model.Restaurant;
import com.bonapp.ujm.myapplication.R;

import java.io.IOException;

import static java.lang.Float.parseFloat;

public class MaCarteMenu extends MenuManagerActivity {

    Restaurant restaurant = new Restaurant();

    RecyclerView recyclerView;

    GestionPlatAdapter adapter;

    ImageView conteneur;
    Bitmap bmp;

    AlertDialog.Builder builder;
    long id_restau;
    int onglet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        Intent intent = getIntent();
        id_restau = intent.getLongExtra("id_restau",1);

        builder = new AlertDialog.Builder(this);

        RepoRestaurant repoRestaurant = new RepoRestaurant(this);
        repoRestaurant.open();

        restaurant = repoRestaurant.selectionnerProfil(id_restau);

        Toast.makeText(MaCarteMenu.this,
                "Vous avez "+ restaurant.plats.size()+" plats",
                Toast.LENGTH_LONG ).show();


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

    public void savePlat(String prixSt, String nom, String descrip){
        if (prixSt.equals("") || nom.equals("")){
            Toast.makeText(MaCarteMenu.this,
                    "Veuillez saisir au moins un nom et prix SVP!",
                    Toast.LENGTH_LONG ).show();
        }
        else {
            //Sauvegarde du plat en BD
            RepoPlat repoPlat = new RepoPlat(MaCarteMenu.this);
            repoPlat.open();
            Plat plat = new Plat(nom, parseFloat(prixSt), descrip, onglet, id_restau);
            long id_plat = repoPlat.ajouter(plat);
            plat.setId(id_plat);
            repoPlat.close();

            //Sauvegarde de l'Image en BD
            RepoImage repoImage = new RepoImage(MaCarteMenu.this);
            repoImage.open();
            Image image = new Image(bmp, id_plat);
            long id_img = repoImage.ajouter(image);
            image.setId(id_img);
            repoImage.close();

            plat.setImage(image);
            switch (onglet){
                case 1:
                    restaurant.plats.add(plat);
                    adapter = new GestionPlatAdapter(MaCarteMenu.this, restaurant.plats, builder);
                    break;
                case 2:
                    restaurant.entrees.add(plat);
                    adapter = new GestionPlatAdapter(MaCarteMenu.this, restaurant.entrees, builder);
                    break;
                case 3:
                    restaurant.desserts.add(plat);
                    adapter = new GestionPlatAdapter(MaCarteMenu.this, restaurant.desserts, builder);
                    break;
            }
        }
    }

    public  void ajoutNouv(){
        final View myView;
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
                                 String prixSt = ((EditText) myView.findViewById(R.id.prix_nouv_plat)).getText().toString();
                                 String nom = ((EditText) myView.findViewById(R.id.nom_nouv_plat)).getText().toString();
                                 String descrip = ((EditText) myView.findViewById(R.id.desc_nouv_plat)).getText().toString();

                                 //Sauvegarde en VD
                                 savePlat(prixSt,nom,descrip);

                                 Toast.makeText(MaCarteMenu.this,
                                             "Le nouveau plat est enregistrée en BD !",
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
                conteneur = myView.findViewById(R.id.img_nouv_plat);
                builder.setTitle("Ajout d'un nouvelle entrée")
                        .setView(myView)
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //ici on traite le formulaire et ajout en BD dans les entrées
                                String prixSt = ((EditText) myView.findViewById(R.id.prix_nouv_plat)).getText().toString();
                                String nom = ((EditText) myView.findViewById(R.id.nom_nouv_plat)).getText().toString();
                                String descrip = ((EditText) myView.findViewById(R.id.desc_nouv_plat)).getText().toString();

                                //Sauvegarde en VD
                                savePlat(prixSt,nom,descrip);

                                Toast.makeText(MaCarteMenu.this,
                                        "La nouvelle entrée est enregistrée en BD !",
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
                conteneur = myView.findViewById(R.id.img_nouv_plat);
                builder.setTitle("Ajout d'un nouveau Dessert")
                        .setView(myView)
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //ici on traite le formulaire et ajout en BD dans les Desserts
                                String prixSt = ((EditText) myView.findViewById(R.id.prix_nouv_plat)).getText().toString();
                                String nom = ((EditText) myView.findViewById(R.id.nom_nouv_plat)).getText().toString();
                                String descrip = ((EditText) myView.findViewById(R.id.desc_nouv_plat)).getText().toString();

                                //Sauvegarde en VD
                                savePlat(prixSt,nom,descrip);

                                Toast.makeText(MaCarteMenu.this,
                                        "Le nouveau dessert est enregistrée en BD !",
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

            bmp = (Bitmap) data.getExtras().get("data");
            adapter.setUri(imageUri);
        }
        else if (resultCode == RESULT_OK && requestCode == 30){
            Uri imageUri = data.getData();
           // bmp = (Bitmap) data.getExtras().get("data");
            try {
                bmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //set the image in an ImageView
            conteneur.setImageBitmap(bmp);
        }
    }
}
