package com.bonapp.ujm.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nianfo on 05/03/2018.
 */

public class RepoRestaurant extends BaseDonnees {

    public Context context;
    public static final String TABLE = "restaurant";
    private static final String TABLE_CREATE =
            "CREATE TABLE  restaurant("+
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nom TEXT, " +
                    "email TEXT, " +
                    "password TEXT, " +
                    "telephone TEXT,"+
                    "description TEXT);";

    public RepoRestaurant(Context context) {
        super(context, TABLE_CREATE, TABLE);
        this.context = context;
    }


    public long  ajouteRestaurant(Restaurant r){
        ContentValues contentValues = new ContentValues();

        contentValues.put("nom",r.getNom());
        contentValues.put("email",r.getEmail());
        contentValues.put("password",r.getMot_passe());
        contentValues.put("telephone",r.getTel());
        contentValues.put("description"," ");
       return DB.insert("restaurant",null,contentValues);

    }

    //Selectionner un restaurant après recherche
    public Restaurant selectionnerAccueil(long id){
        Cursor c = DB.rawQuery("SELECT id, nom"+
                " FROM "+ TABLE +" where id = ?" , new String[]{""+id} );

        int idd = c.getInt(0);
        String nom = c.getString(1);
        //int img = c.getInt(2);

        Adresse ad = new RepoAdresse(context).selectionner(idd);

        return  new Restaurant(idd, nom, 0, ad );
    }

    //Selectionner mon restaurant
    public Restaurant selectionnerProfil(long id){
        Cursor c = DB.rawQuery("SELECT id, nom, email, telephone, description"+
                " FROM "+ TABLE +" where id = ?" , new String[]{""+id} );

        String nom = c.getString(1);
        String email = c.getString(2);
        String tel = c.getString(3);
      //  int img = c.getInt(4);
        String descrip = c.getString(4);

        Adresse ad = new RepoAdresse(context).selectionner(id);

        RepoPlat repoPlat = new RepoPlat(context);

        ArrayList<Plat> entree = repoPlat.selectionner(id,1);
        ArrayList<Plat> chaud = repoPlat.selectionner(id,2);
        ArrayList<Plat> dessert = repoPlat.selectionner(id,3);

        ArrayList<TypeCuisine> types = new RepoTypeCuisineRestaurant(context).selectionnerType(id);

        return  new Restaurant(id, nom,tel,descrip,0,types,chaud,entree,dessert, ad);
    }
}
