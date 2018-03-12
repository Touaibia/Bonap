package com.bonapp.ujm.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by maham on 05/03/2018.
 */

public class RepoPlat extends BaseDonnees {
    public static final String TABLE_NAME = "plat";
    public static final String KEY = "id";
    public static final String NOM = "nom";
    public static final String IMAGE = "image";
    public static final String TYPE = "type";
    public static final String PRIX = "prix";
    public static final String DESCRIP = "description";
    public static final String RESTAU = "id_restau";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME +
            " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOM + " CHAR(60), " + IMAGE + " INTEGER, " + TYPE + " INTEGER, "+
            PRIX + " REAL, " + DESCRIP + " TEXT" + RESTAU + " INTEGER);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public RepoPlat(Context context) {
        super(context);
    }

    public void ajouter(Plat pl){
        ContentValues contVal = new ContentValues();

        contVal.put(NOM, pl.getNom());
        contVal.put(IMAGE, pl.getImage());
        contVal.put(TYPE, pl.getType());
        contVal.put(PRIX, pl.getPrix());
        contVal.put(DESCRIP, pl.getDescription());

        DB.insert(TABLE_NAME,null,contVal);
    }

    public void supprimer(long id){
        DB.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    public void modifier(Plat pl){
        ContentValues contVal = new ContentValues();

        contVal.put(NOM, pl.getNom());
        contVal.put(IMAGE, pl.getImage());
        contVal.put(TYPE, pl.getType());
        contVal.put(PRIX, pl.getPrix());
        contVal.put(DESCRIP, pl.getDescription());

        DB.update(TABLE_NAME, contVal, KEY  + " = ?", new String[] {String.valueOf(pl.getId())});

    }

    public ArrayList<Plat> selectionner(long id, int type){
        Cursor c = DB.rawQuery("SELECT "+ KEY +", "+ NOM +", "+ IMAGE +", "+ PRIX +", "+ DESCRIP +
                " FROM "+ TABLE_NAME +" where type = ? AND id_restau = ?", new String[]{""+id,""+type} );

        ArrayList<Plat> lesPlats = new ArrayList<>();

        while(c.moveToNext()){
            int num = c.getInt(0);
            String nom = c.getString(1);
            int img = c.getInt(2);
            float prix = c.getFloat(3);
            String descrip = c.getString(4);

            lesPlats.add(new Plat(num,img,nom,prix,descrip));
        }

        return lesPlats;
    }

    //Selectionner un Plat
    public Plat selectionnerPlat(long id){
        Cursor c = DB.rawQuery("SELECT "+ KEY +", "+ NOM +", "+ IMAGE +", "+ PRIX +", "+ DESCRIP +
                " FROM "+ TABLE_NAME +"where id = ?", new String[]{""+id} );

        int num = c.getInt(0);
        String nom = c.getString(1);
        int img = c.getInt(2);
        float prix = c.getFloat(3);
        String descrip = c.getString(4);

        Plat plat = new Plat(num,img,nom,prix,descrip) ;


        return plat;
    }
}
