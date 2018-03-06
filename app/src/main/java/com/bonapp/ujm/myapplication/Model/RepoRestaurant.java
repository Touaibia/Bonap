package com.bonapp.ujm.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nianfo on 05/03/2018.
 */

public class RepoRestaurant {
    BaseDonnees db;
    Context context;
    String TABLE = "restaurant";

    private String TABLE_CREATE =
            "CREATE TABLE  restaurant("+
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nom TEXT, " +
                    "email TEXT, " +
                    "password TEXT, " +
                    "adresse INTEGER, " +
                    "telephone TEXT);";


    public RepoRestaurant(Context context){
       Toast.makeText(context,"ouverure ok",Toast.LENGTH_LONG).show();
        db = new BaseDonnees(context, TABLE_CREATE);
        //db.DB.execSQL(TABLE_CREATE);
        this.context = context;
        db.open();

    }

    public void ajouteRestaurant(Restaurant r){
        ContentValues contentValues = new ContentValues();

        contentValues.put("nom",r.getNom());
        contentValues.put("email",r.getEmail());
        contentValues.put("password",r.getMot_passe());
        contentValues.put("adresse",r.getAdresse().getId());
        contentValues.put("telephone",r.getTel());
        //Toast.makeText(context,r.getNom()+" "+r.getEmail()+" "+r.getMot_passe()+" "+r.getAdresse().getId()+" "+r.getTel(),Toast.LENGTH_LONG).show();
        if(db.DB.insert(TABLE,null,contentValues)==-1){

            Toast.makeText(context,"echec d'insertion restaurant",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context,"restaurant insertion ok",Toast.LENGTH_LONG).show();
        }

    }

    public List<Restaurant> getAllResto(){
        Cursor cursor = db.DB.rawQuery("select* from restaurant",null);
        List idt = new ArrayList();
        while(cursor.moveToNext()){
            Cursor ad = db.DB.rawQuery("select* from adresse where id ="+cursor.getInt(4),null);
            ad.moveToNext();
            Adresse d = new Adresse(ad.getString(2),ad.getString(3),ad.getString(4),ad.getString(5));
            idt.add(new Restaurant(cursor.getString(2),cursor.getString(3),cursor.getString(4),d,
                    cursor.getString(6)));
        }
        return idt;
    }
    public void close(){db.close();}
}
