package com.bonapp.ujm.myapplication.Model;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bonapp.ujm.myapplication.Controller.GestionProfil;
import com.bonapp.ujm.myapplication.Controller.MaCarteMenu;
import com.bonapp.ujm.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.support.v4.app.ActivityCompat.startActivityForResult;

/**
 * Created by maham on 19/02/2018.
 */

public class GestionPlatAdapter extends RecyclerView.Adapter<GestionPlatAdapter.MyViewHolder> {
    List<Restaurant.Plats> plats;

    Context myContext;

    AlertDialog.Builder builder;
    ImageView conteneur;

    public GestionPlatAdapter(MaCarteMenu maCarteMenu, List<Restaurant.Plats> list, AlertDialog.Builder build) {
        this.plats = list;
        this.builder = build;
        this.myContext = maCarteMenu;
    }

    @Override
    public int getItemCount() {
        return plats.size();
    }

    @Override
    public GestionPlatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.gestion_plat, parent, false);
        return new GestionPlatAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Restaurant.Plats plat = plats.get(position);

        holder.optPlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup = new PopupMenu(view.getContext(), holder.optPlat);

                popup.inflate(R.menu.option_plat);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.mod_plat:
                                //Affichage du formulaire de modification
                                afficheFormModif(holder);
                                break;
                            case R.id.sup_plat:
                                //Suppression du plat

                                break;

                        }
                        return false;
                    }
                });

                popup.show();
            }
        });

        holder.display(plat);
    }

    public void afficheFormModif(final MyViewHolder holder){
        View myView;
        LayoutInflater li = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myView = li.inflate(R.layout.modif_plat,null);

        final ImageView edit_img_plat = (ImageView) myView.findViewById(R.id.edit_img_plat);
        final EditText edit_nom_plat = (EditText) myView.findViewById(R.id.edit_nom_plat);
        EditText edit_desc_plat = (EditText) myView.findViewById(R.id.edit_desc_plat);
        Button but_img = (Button) myView.findViewById(R.id.but_edit_img);

        but_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImageFromGalery(view);
            }
        });

        edit_img_plat.setImageDrawable(holder.imageView.getDrawable());
        edit_nom_plat.setText(holder.nom.getText());
        edit_desc_plat.setText("La description n'est pas encore definie");

        conteneur = edit_img_plat;

        builder.setTitle("Modifier le Plat")
                .setView(myView)
                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // ici on traite le formulaire
                        holder.nom.setText(edit_nom_plat.getText());

                        holder.imageView.setImageDrawable(edit_img_plat.getDrawable());

                        Toast.makeText(myContext,
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

    }

    public void confirSuppression(){
        builder.setMessage("Voulez vous vraiment le supprimer ?")
                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // ici on traite la suppression de l'élément
                        Toast.makeText(myContext,
                                "La Suppression est prise en compte est enregistrée en BD !",
                                Toast.LENGTH_LONG ).show();

                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.show();
    }

    //La methode pour selectionner une image
    public void selectImageFromGalery(View v){
        Intent photoPicker = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        ((Activity) myContext).startActivityForResult(photoPicker,20);
    }

    public void setUri(Uri imgUri){
        conteneur.setImageURI(imgUri);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nom;
        TextView optPlat;

        public MyViewHolder(View view) {
            super(view);
            nom = (TextView) view.findViewById(R.id.nom_plat);
            imageView = (ImageView) view.findViewById(R.id.img_plat);
            optPlat = (TextView) view.findViewById(R.id.plat_opt);
        }

        public void display(Restaurant.Plats plat) {
            nom.setText(plat.nom);
            imageView.setImageResource(plat.image);
        }
    }

}
