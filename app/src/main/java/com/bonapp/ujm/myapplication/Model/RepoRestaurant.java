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
    //BaseDonnees db;
    public Context context;
    public static final String TABLE = "restaurant";
    private static final String TABLE_CREATE =
            "CREATE TABLE  restaurant("+
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nom TEXT, " +
                    "email TEXT, " +
                    "password TEXT, " +
                    "adresse INTEGER, " +
                    "telephone TEXT"+
                    "description TEXT"+
                    "image INTEGER);";

    public RepoRestaurant(Context context) {
        super(context, TABLE_CREATE, TABLE);
        this.context = context;
    }

    public boolean identification(String username, String pwd){
        Cursor cursor = DB.rawQuery("select * from restaurant " +
                "where nom = "+username+" and password = "+pwd,null);

        if (cursor.moveToNext()){
            return true;
        }
        return false;
    }

    public long  ajouteRestaurant(Restaurant r){
        ContentValues contentValues = new ContentValues();

        contentValues.put("nom",r.getNom());
        contentValues.put("email",r.getEmail());
        contentValues.put("password",r.getMot_passe());
        contentValues.put("adresse",r.getAdresse().getId());
        contentValues.put("telephone",r.getTel());
        contentValues.put("description",r.getDescription());
        contentValues.put("image",r.getImage());

        //Toast.makeText(context,r.getNom()+" "+r.getEmail()+" "+r.getMot_passe()+" "+r.getAdresse().getId()+" "+r.getTel(),Toast.LENGTH_LONG).show();
       return DB.insert("restaurant",null,contentValues);

    }

//    public List<Restaurant> getAllResto(){
//        Cursor cursor = DB.rawQuery("select* from restaurant",null);
//        List idt = new ArrayList();
//        while(cursor.moveToNext()){
//            //Cursor ad = db.DB.rawQuery("select* from adresse where id ="+cursor.getInt(4),null);
//           // ad.moveToNext();
//           // Adresse d = new Adresse(ad.getString(2),ad.getString(3),ad.getString(4),ad.getString(5));
//            idt.add(new Restaurant(cursor.getString(1),cursor.getString(2),cursor.getString(3),
//                    new Adresse(),
//                    cursor.getString(5)));
//        }
//        return idt;
//    }

    //Selectionner un restaurant après recherche
    public Restaurant selectionnerAccueil(long id){
        Cursor c = DB.rawQuery("SELECT id, nom, image"+
                " FROM "+ TABLE +" where id = ?" , new String[]{""+id} );

        int idd = c.getInt(0);
        String nom = c.getString(1);
        int img = c.getInt(2);

        Adresse ad = new RepoAdresse(context).selectionner(idd);

        return  new Restaurant(idd, nom, img, ad );
    }

    //Selectionner mon restaurant
    public Restaurant selectionnerProfil(long id){
        Cursor c = DB.rawQuery("SELECT id, nom, email, telephone, image, description"+
                " FROM "+ TABLE +" where id = ?" , new String[]{""+id} );

        String nom = c.getString(1);
        String email = c.getString(2);
        String tel = c.getString(3);
        int img = c.getInt(4);
        String descrip = c.getString(5);

        Adresse ad = new RepoAdresse(context).selectionner(id);

        RepoPlat repoPlat = new RepoPlat(context);

        ArrayList<Plat> entree = repoPlat.selectionner(id,1);
        ArrayList<Plat> chaud = repoPlat.selectionner(id,2);
        ArrayList<Plat> dessert = repoPlat.selectionner(id,3);

        ArrayList<TypeCuisine> types = new RepoTypeCuisineRestaurant(context).selectionnerType(id);

        return  new Restaurant(id, nom,tel,descrip,img,types,chaud,entree,dessert, ad);
    }
}
