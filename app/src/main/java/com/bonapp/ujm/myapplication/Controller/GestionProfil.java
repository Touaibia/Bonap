package com.bonapp.ujm.myapplication.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bonapp.ujm.myapplication.Model.Adresse;
import com.bonapp.ujm.myapplication.Model.RepoRestaurant;
import com.bonapp.ujm.myapplication.Model.RepoTypeCuisine;
import com.bonapp.ujm.myapplication.Model.RepoTypeCuisineRestaurant;
import com.bonapp.ujm.myapplication.Model.Restaurant;
import com.bonapp.ujm.myapplication.Model.TypeAmbianceAdapter;
import com.bonapp.ujm.myapplication.Model.TypeCuisine;
import com.bonapp.ujm.myapplication.Model.TypeCuisineAdapter;
import com.bonapp.ujm.myapplication.Model.TypeCuisineRestaurant;
import com.bonapp.ujm.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

/**
 * Created by maham on 23/01/2018.
 */

public class GestionProfil extends MenuManagerActivity {

    AlertDialog.Builder builder;
    String type="";

    long id_restau;
    Restaurant restau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

//        Intent intent = getIntent();
//
//        id_restau = intent.getLongExtra("id_restau",1);

        id_restau = 1;

        RepoRestaurant repoRestau = new RepoRestaurant(this);
        repoRestau.open();

        restau = repoRestau.selectionnerProfil(id_restau);
        Log.d("LE NOM", restau.getNom());
        Adresse adresse = restau.getAdresse();

        TextView nom_restau = (TextView) findViewById(R.id.nom_restau);
        TextView ad_restau = (TextView) findViewById(R.id.adr_restau);
        TextView tel = (TextView) findViewById(R.id.tel_restau);
        TextView descrip = (TextView) findViewById(R.id.descrip_restau);

        nom_restau.setText(restau.getNom());

        ad_restau.setText(adresse.getNumero()+" "+adresse.getType_voie()+" "+adresse.getIntitule()+" "+adresse.getCode_postal());
        tel.setText(restau.getTel());
        descrip.setText("A Propos de nous : "+restau.getDescription());

        Button modifInfoGen = (Button) findViewById(R.id.mod_info_gen);

        modifInfoGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modif_info_gen();
            }
        });

        //Affectation de la liste des types de cuisines
        setTypeCuisine();

        Button ajout_type_cuis = (Button) findViewById(R.id.ajout_type_cuis);
        ajout_type_cuis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ajoutTypeCuisine();
            }
        });

        Button accesCarte = (Button) findViewById(R.id.voir_carte);
        accesCarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GestionProfil.this, MaCarteMenu.class);
                intent.putExtra("id_restau",id_restau);
                startActivity(intent);
            }
        });

        builder = new AlertDialog.Builder(this);

        RepoTypeCuisineRestaurant repoTypeCuisineRestaurant = new RepoTypeCuisineRestaurant(this);
        repoTypeCuisineRestaurant.open();
        ArrayList<TypeCuisineRestaurant> mesTypes = repoTypeCuisineRestaurant.selectAll();
        repoTypeCuisineRestaurant.close();

        for (int i = 0; i < mesTypes.size(); i++) {
            TypeCuisineRestaurant tr = mesTypes.get(i);
            Log.d("Ligne "+i+" ", ""+tr.getId() +" "+tr.getId_type()+" "+tr.getId_restau());
        }
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
        final EditText editEmail = (EditText) myView.findViewById(R.id.edit_email_restau);

        //on charge les données à modifer dans le formulaire
        editNom.setText(nomRestau.getText());
        editTel.setText(numRestau.getText());
        editCodePost.setText(restau.getAdresse().getCode_postal()+"");
        editNumRue.setText(restau.getAdresse().getNumero());
        editLabel.setText(restau.getAdresse().getIntitule());
        editEmail.setText(restau.getEmail());

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

    public void setTypeCuisine(){
        TextView text = (TextView) findViewById(R.id.type_cuis_text);
        if(restau.getTypeCuisines().size() == 0){
            text.setVisibility(View.VISIBLE);
        }
        else{
            text.setVisibility(View.INVISIBLE);
            RecyclerView rv = (RecyclerView) findViewById(R.id.list_type_cuis);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(new TypeCuisineAdapter(id_restau, restau.getTypeCuisines(),GestionProfil.this));
        }
    }

    public void ajoutTypeCuisine(){
        final View myView;
        myView = getLayoutInflater().inflate(R.layout.ajout_type,null);

        final EditText nouvType = (EditText) myView.findViewById(R.id.nouv_type);

        //Recupératio de tous les types de cuisine en BD
        RepoTypeCuisine repoTypeCuisine = new RepoTypeCuisine(GestionProfil.this);
        repoTypeCuisine.open();
        ArrayList<TypeCuisine> lesTypes = repoTypeCuisine.selectionner();
        repoTypeCuisine.close();

        //Création d'une liste nomType_id pour le spinner
        List<String> tab = new ArrayList<>();
        for (int i=0; i < lesTypes.size(); i++){
            tab.add(""+lesTypes.get(i).getNom()+"_"+lesTypes.get(i).getId());
        }

        tab.add("Autre");

        final Spinner spinner = (Spinner) myView.findViewById(R.id.list_type);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter;
        adapter = new  ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tab);
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

                        if (spinner.getSelectedItem().toString().equals("Autre") && nouvType.getText().length() != 0){

                            //Ajout du type dans la liste
                            RepoTypeCuisine repoTypeCuisine = new RepoTypeCuisine(GestionProfil.this);
                            repoTypeCuisine.open();
                            TypeCuisine nouv = new TypeCuisine(nouvType.getText().toString(),0,"Un nouveau type");
                            long id_type = repoTypeCuisine.ajouter(nouv);
                            repoTypeCuisine.close();
                            nouv.setId(id_type);
                            restau.getTypeCuisines().add(nouv);

                            //Ajout du type dans les types du Restau en BD
                            RepoTypeCuisineRestaurant repoTypeRestau = new RepoTypeCuisineRestaurant(GestionProfil.this);
                            repoTypeRestau.open();
                            long val = repoTypeRestau.ajouter(new TypeCuisineRestaurant(id_type,id_restau));
                            repoTypeRestau.close();

                            Toast.makeText(GestionProfil.this,
                                    "Le Nouveau Type est bien  ajouté en BD avec ID = "+val,Toast.LENGTH_LONG ).show();

                            setTypeCuisine();
                        }
                        else if (!spinner.getSelectedItem().toString().equals("Autre")){
                            String tab[] = spinner.getSelectedItem().toString().split("_");

                            //Ajout du type dans les types du Restau en BD
                            RepoTypeCuisineRestaurant repoTypeRestau = new RepoTypeCuisineRestaurant(GestionProfil.this);
                            repoTypeRestau.open();
                            long id_type = parseLong(tab[1]);
                            repoTypeRestau.ajouter(new TypeCuisineRestaurant(id_type,id_restau));
                            repoTypeRestau.close();

                            //Ajout du type dans la liste
                            RepoTypeCuisine repoTypeCuisine = new RepoTypeCuisine(GestionProfil.this);
                            repoTypeCuisine.open();
                            TypeCuisine nouv = repoTypeCuisine.selectionner(id_type);

                            restau.getTypeCuisines().add(nouv);
                            setTypeCuisine();

                            Toast.makeText(GestionProfil.this,
                                    "Le Type est bien associé au Restau en BD !",Toast.LENGTH_LONG ).show();
                        }
                        else {
                            Toast.makeText(GestionProfil.this,
                                    "Rien n'a été ajouté !",Toast.LENGTH_LONG ).show();
                        }

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
