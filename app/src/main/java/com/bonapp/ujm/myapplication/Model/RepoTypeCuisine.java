package com.bonapp.ujm.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by maham on 07/03/2018.
 */

public class RepoTypeCuisine extends BaseDonnees {
    public static final String TABLE_NAME = "typeCuisine";
    public static final String KEY = "id";
    public static final String NOM = "nom";
    public static final String IMAGE = "image";
    public static final String DESCRIP = "description";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME +
            " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOM + " CHAR(60), " + IMAGE + " INTEGER, " + DESCRIP + " TEXT);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";


    public RepoTypeCuisine(Context context, String create) {
        super(context, create);
    }

    public void ajouter(TypeCuisine tp){
        ContentValues contVal = new ContentValues();

        contVal.put(NOM, tp.getNom());
        contVal.put(IMAGE, tp.getImage());
        contVal.put(DESCRIP, tp.getDescription());


        DB.insert(TABLE_NAME,null,contVal);
    }

    public void supprimer(int id){
        DB.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    public void modifier(TypeCuisine tp){
        ContentValues contVal = new ContentValues();

        contVal.put(NOM, tp.getNom());
        contVal.put(IMAGE, tp.getImage());
        contVal.put(DESCRIP, tp.getDescription());

        DB.update(TABLE_NAME, contVal, KEY  + " = ?", new String[] {String.valueOf(tp.getId())});

    }

    //Selection tous les types de cuisine
    public ArrayList<TypeCuisine> selectionner(){
        Cursor c = DB.rawQuery("SELECT "+ KEY +", "+ NOM +", "+ IMAGE +", " + DESCRIP +
                " FROM "+ TABLE_NAME , new String[]{} );

        ArrayList<TypeCuisine> lestypes = new ArrayList<>();

        while(c.moveToNext()){
            int num = c.getInt(0);
            String nom = c.getString(1);
            int img = c.getInt(2);
            String descrip = c.getString(3);

            lestypes.add(new TypeCuisine(num,nom,img,descrip));
        }

        return lestypes;
    }

    public TypeCuisine selectionner(int id){
        Cursor c = DB.rawQuery("SELECT "+ KEY +", "+ NOM +", "+ IMAGE +", " + DESCRIP +
                " FROM "+ TABLE_NAME +" where id = ?" , new String[]{""+id} );

        int num = c.getInt(0);
        String nom = c.getString(1);
        int img = c.getInt(2);
        String descrip = c.getString(3);

        TypeCuisine type = new TypeCuisine(num,nom,img,descrip);

        return type;
    }
}
