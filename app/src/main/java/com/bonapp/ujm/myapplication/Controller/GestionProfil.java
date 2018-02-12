package com.bonapp.ujm.myapplication.Controller;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.bonapp.ujm.myapplication.R;

/**
 * Created by maham on 23/01/2018.
 */

public class GestionProfil extends MenuManagerActivity {

    AlertDialog.Builder builder;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        builder = new AlertDialog.Builder(this);
    }

    protected  int getLayoutId(){
        return R.layout.gestion_profil_manager;
    }

    public void modif_info_gen(){
        View myView;
        myView = getLayoutInflater().inflate(R.layout.pub_simple,null);

        builder.setTitle("Publication Simple")
                .setView(myView)
                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // ici on traite le formulaire
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
}
