package com.bonapp.ujm.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by maham on 23/01/2018.
 */

public class GestionPublication extends MenuManagerActivity implements AdapterView.OnItemSelectedListener {

    AlertDialog.Builder builder;

    ImageView conteneur;

    @Override
    public void onCreate(Bundle savedInstanceState){
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
        //String elem = parent.getItemAtPosition(pos).toString();
        switch (pos){
            case 0:
                break;
            case 1:
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
            case 2:
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
            case 3:
                //Annonce Promotionnelle
                myView = getLayoutInflater().inflate(R.layout.annonce_promo,null);

                //on charge la liste dans le spinner
                Button spinner = (Button) myView.findViewById(R.id.selectMenus);
               /* ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(GestionPublication.this,
                        R.array.list_menu, android.R.layout.simple_spinner_item);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                spinner.setAdapter(adapter);*/

               spinner.setOnClickListener(afficheDialog());

                //On recupere l'ImageView du layout
                conteneur = (ImageView) myView.findViewById(R.id.imgPic);

                builder.setTitle("Offre Promotionnelle")
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
            case 4:
                //Annonce Evenementielle
                myView = getLayoutInflater().inflate(R.layout.annonce_event,null);

                //On recupere l'ImageView du layout
                conteneur = (ImageView) myView.findViewById(R.id.imgPic);

                builder.setTitle("Publication d'un événement local")
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
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public View.OnClickListener afficheDialog(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Création de la liste des choix
                final String[] listElem = getResources().getStringArray(R.array.list_menu) ;

                final boolean[] elemCoch = new boolean[listElem.length];

                final ArrayList<Integer> menusSelect = new ArrayList<>();

                AlertDialog.Builder mbuilder = new AlertDialog.Builder(GestionPublication.this);
                mbuilder.setTitle("Selectionner les Menus");
                //affectation de la liste à la boite de dialogue
                mbuilder.setMultiChoiceItems(listElem,elemCoch, multiChoiceClickListener(menusSelect));

                mbuilder.setPositiveButton("Suivant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int var) {
                        String laSelection = "";
                        for (int i=0; i < menusSelect.size(); i++){
                            laSelection = laSelection+listElem[menusSelect.get(i)]+" - ";
                        }

                        Toast.makeText(GestionPublication.this,laSelection, Toast.LENGTH_LONG).show();
                    }
                });

                mbuilder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int var) {
                        for (int i = 0; i < elemCoch.length; i++){
                            elemCoch[i]=false;
                        }
                        menusSelect.clear();
                        dialogInterface.dismiss();
                    }
                });

                mbuilder.show();
            }
        };

        return listener;
    }

    //pour les choix Multiple
    public DialogInterface.OnMultiChoiceClickListener multiChoiceClickListener(final ArrayList<Integer> list){
        final DialogInterface.OnMultiChoiceClickListener multi = new DialogInterface.OnMultiChoiceClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int pos, boolean bool) {
                if (bool){
                    list.add(pos);
                }else{
                    list.remove((Integer.valueOf(pos)));
                }
            }
        };

        return multi;
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
}
