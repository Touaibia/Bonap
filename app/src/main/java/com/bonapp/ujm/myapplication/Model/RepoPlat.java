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
    public static final String TYPE = "type";
    public static final String PRIX = "prix";
    public static final String DESCRIP = "description";
    public static final String RESTAU = "id_restau";

    public Context context;

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME +
            " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOM + " CHAR(60), "  + TYPE + " INTEGER, "+
            PRIX + " REAL, " + DESCRIP + " TEXT" + RESTAU + " INTEGER);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public RepoPlat(Context context) {
        super(context);
        this.context = context;
    }

    public long ajouter(Plat pl){
        ContentValues contVal = new ContentValues();

        contVal.put(NOM, pl.getNom());
        contVal.put(TYPE, pl.getType());
        contVal.put(PRIX, pl.getPrix());
        contVal.put(DESCRIP, pl.getDescription());
        contVal.put(RESTAU, pl.getId_restau());

        return DB.insert(TABLE_NAME,null,contVal);
    }

    public int supprimer(long id){
        return DB.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    public void modifier(Plat pl){
        ContentValues contVal = new ContentValues();

        contVal.put(NOM, pl.getNom());
        contVal.put(TYPE, pl.getType());
        contVal.put(PRIX, pl.getPrix());
        contVal.put(DESCRIP, pl.getDescription());
        contVal.put(RESTAU, pl.getId_restau());

        DB.update(TABLE_NAME, contVal, KEY  + " = ?", new String[] {String.valueOf(pl.getId())});

        RepoImage repoImage = new RepoImage(context);
        repoImage.open();
        repoImage.modifier(pl.getImage());
        repoImage.close();

    }

    public ArrayList<Plat> selectionner(long id, int type){
        Cursor c = DB.rawQuery("SELECT "+ KEY +", "+ NOM +", "+ PRIX +", "+ DESCRIP +
                " FROM "+ TABLE_NAME +" where type = ? AND id_restau = ?", new String[]{""+id,""+type} );

        ArrayList<Plat> lesPlats = new ArrayList<>();

        while(c.moveToNext()){
            int num = c.getInt(0);
            String nom = c.getString(1);
            float prix = c.getFloat(2);
            String descrip = c.getString(3);

            RepoImage repoImage = new RepoImage(context);
            repoImage.open();
            Image image = repoImage.selectionner(num);

            repoImage.close();

            lesPlats.add(new Plat(num,image,nom,prix,descrip));
        }

        return lesPlats;
    }

    //Selectionner un Plat
    public Plat selectionnerPlat(long id){
        Cursor c = DB.rawQuery("SELECT "+ KEY +", "+ NOM  +", "+ PRIX +", "+ DESCRIP +
                " FROM "+ TABLE_NAME +"where id = ?", new String[]{""+id} );

        c.moveToNext();

        int num = c.getInt(0);
        String nom = c.getString(1);
        float prix = c.getFloat(2);
        String descrip = c.getString(3);
        RepoImage repoImage = new RepoImage(context);
        repoImage.open();
        Image image = repoImage.selectionner(num);

        repoImage.close();

        Plat plat = new Plat(num,image,nom,prix,descrip);
        return plat;
    }
}
