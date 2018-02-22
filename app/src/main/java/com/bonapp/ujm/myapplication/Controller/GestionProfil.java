package com.bonapp.ujm.myapplication.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bonapp.ujm.myapplication.Model.TypeAmbianceAdapter;
import com.bonapp.ujm.myapplication.Model.TypeCuisineAdapter;
import com.bonapp.ujm.myapplication.R;

/**
 * Created by maham on 23/01/2018.
 */

public class GestionProfil extends MenuManagerActivity {

    AlertDialog.Builder builder;
    String type="";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        Button modifInfoGen = (Button) findViewById(R.id.mod_info_gen);

        modifInfoGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modif_info_gen();
            }
        });

        //Affectation de la liste des types de cuisines
        RecyclerView rv = (RecyclerView) findViewById(R.id.list_type_cuis);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new TypeCuisineAdapter());

        Button ajout_type_cuis = (Button) findViewById(R.id.ajout_type_cuis);
        ajout_type_cuis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ajoutTypeCuisine();
            }
        });

        //Affectation de la liste des types d'ambiances
        RecyclerView rvv = (RecyclerView) findViewById(R.id.list_type_amb);
        rvv.setLayoutManager(new LinearLayoutManager(this));
        rvv.setAdapter(new TypeAmbianceAdapter());

        Button accesCarte = (Button) findViewById(R.id.voir_carte);
        accesCarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GestionProfil.this, MaCarteMenu.class));
            }
        });

        builder = new AlertDialog.Builder(this);
    }



    protected  int getLayoutId(){
        return R.layout.gestion_profil_manager;
    }

    public void modif_info_gen(){
        //Recuperation des infos generales d'origine
        final TextView nomRestau = (TextView) findViewById(R.id.nom_restau);
        final TextView adrRestau = (TextView) findViewById(R.id.adr_restau);
        final TextView numRestau = (TextView) findViewById(R.id.tel_restau);

        View myView;
        myView = getLayoutInflater().inflate(R.layout.info_gen,null);

        //Les champs de saisie
        final EditText editNom = (EditText) myView.findViewById(R.id.edit_nom_restau);
        final EditText editTel = (EditText) myView.findViewById(R.id.edit_tel_restau);
        final EditText editNumRue = (EditText) myView.findViewById(R.id.edit_num_ad);
        final EditText editLabel = (EditText) myView.findViewById(R.id.edit_label_ad);
        final EditText editCodePost = (EditText) myView.findViewById(R.id.edit_code_post);

        //on charge les données à modifer dans le formulaire
        editNom.setText(nomRestau.getText());
        editTel.setText(numRestau.getText());


        Spinner spinner = (Spinner) myView.findViewById(R.id.edit_type_voie);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_voie, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view;
                type = (String) textView.getText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        builder.setTitle("Publication Simple")
                .setView(myView)
                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // ici on traite le formulaire
                        nomRestau.setText(editNom.getText());

                        adrRestau.setText(editNumRue.getText()+" "+type+" "+editLabel.getText()+" "+
                                editCodePost.getText());

                        numRestau.setText(editTel.getText());


                        Toast.makeText(GestionProfil.this,
                                "Les Modificatons sont prises en compte est enregistrée en BD !",Toast.LENGTH_LONG ).show();

                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.show();
    }

    public void ajoutTypeCuisine(){
        final View myView;
        myView = getLayoutInflater().inflate(R.layout.ajout_type,null);

        final EditText nouvType = (EditText) myView.findViewById(R.id.nouv_type);

        Spinner spinner = (Spinner) myView.findViewById(R.id.list_type);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_voie, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {

                String elem = parent.getItemAtPosition(pos).toString();

                if (elem.equals("Autre")){
                    //afficher un champ de saisie
                    nouvType.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        builder.setTitle("Ajout d'un Nouveau Type")
                .setView(myView)
                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        Toast.makeText(GestionProfil.this,
                                "Le Type est ajouté !",Toast.LENGTH_LONG ).show();

                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.show();
    }
}
