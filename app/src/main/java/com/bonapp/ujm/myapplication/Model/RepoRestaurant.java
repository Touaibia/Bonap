package com.bonapp.ujm.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
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
                    "telephone TEXT, "+
                    "description TEXT);";

    public RepoRestaurant(Context context) {
        super(context);
        this.context = context;
        this.tableName = "restaurant";
    }


    public long  ajouteRestaurant(Restaurant r){
        ContentValues contentValues = new ContentValues();

        contentValues.put("nom",r.getNom());
        contentValues.put("email",r.getEmail());
        contentValues.put("password",r.getMot_passe());
        contentValues.put("telephone",r.getTel());
        contentValues.put("description","la description de votre restaurant");
       // contentValues.put("image",r.getImage());

       return DB.insert("restaurant",null,contentValues);

    }


    //Selectionner un restaurant après recherche
    public Restaurant selectionnerAccueil(long id){
        Cursor c = DB.rawQuery("SELECT id, nom"+
                " FROM "+ TABLE +" where id = ?" , new String[]{""+id} );

        int idd = c.getInt(0);
        String nom = c.getString(1);
       // int img = c.getInt(2);
        /*
            RepoImage repo = new RepoImage(this)
            int img = repo.getImage(id)
            à faire.

         */

        Adresse ad = new RepoAdresse(context).selectionner(idd);

        return  new Restaurant(idd, nom, 0, ad );
    }

    //Selectionner mon restaurant
    public Restaurant selectionnerProfil(long id){
        Cursor c = DB.rawQuery("SELECT id, nom, email, telephone, description"+
                " FROM "+ TABLE +" where id = ?" , new String[]{""+id} );

        c.moveToNext();

        String nom = c.getString(1);
        String email = c.getString(2);
        String tel = c.getString(3);
        //int img = c.getInt(4);
        String descrip = c.getString(4);

        RepoAdresse repoAd = new RepoAdresse(context);
        repoAd.open();
        Adresse ad = repoAd.selectionner(id);
        repoAd.close();

        RepoPlat repoPlat = new RepoPlat(context);

        repoPlat.open();

        ArrayList<Plat> entree = repoPlat.selectionner(id,2);
        ArrayList<Plat> chaud = repoPlat.selectionner(id,1);
        ArrayList<Plat> dessert = repoPlat.selectionner(id,3);

        repoPlat.close();

        RepoTypeCuisineRestaurant repoTypeCuisineRes = new RepoTypeCuisineRestaurant(context);
        repoTypeCuisineRes.open();

        ArrayList<TypeCuisine> types = repoTypeCuisineRes.selectionnerType(id);

        Log.d("Mon MESSAGE : ", types.size()+"");

        repoTypeCuisineRes.close();

        return  new Restaurant(id, nom,tel,descrip,0,types,chaud,entree,dessert, ad);
    }
}
