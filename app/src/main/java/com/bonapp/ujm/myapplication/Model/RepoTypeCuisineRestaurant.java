package com.bonapp.ujm.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by maham on 07/03/2018.
 */

public class RepoTypeCuisineRestaurant extends BaseDonnees {

    public static final String TABLE_NAME = "typeCuisineRestaurant";
    public static final String KEY = "id";
    public static final String TYPE = "type";
    public static final String RESTAU = "id_restau";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME +
            " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TYPE + " INTEGER, " + RESTAU + " INTEGER);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";


    public Context context;
    public RepoTypeCuisineRestaurant(Context context, String create) {
        super(context, TABLE_CREATE,TABLE_NAME);
        this.context = context;
    }

    public void ajouter(TypeCuisineRestaurant tpr){
        ContentValues contVal = new ContentValues();

        contVal.put(RESTAU, tpr.getId_restau());
        contVal.put(TYPE, tpr.getId_type());

        DB.insert(TABLE_NAME,null,contVal);
    }

    public void supprimer(int id){
        DB.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    public void modifier(TypeCuisineRestaurant tpr){
        ContentValues contVal = new ContentValues();

        contVal.put(RESTAU, tpr.getId_restau());
        contVal.put(TYPE, tpr.getId_type());

        DB.update(TABLE_NAME, contVal, KEY  + " = ?", new String[] {String.valueOf(tpr.getId())});

    }

    //Selection des Restaurants en fonction d
    public ArrayList<Restaurant> selectionner(int id_type){
        Cursor c = DB.rawQuery("SELECT "+ KEY +", "+ RESTAU +
                " FROM "+ TABLE_NAME +" where id_type = ?", new String[]{""+id_type} );

        ArrayList<Restaurant> lesRestaus = new ArrayList<>();

        while(c.moveToNext()){
            int num = c.getInt(1);

           // lesRestaus.add(new Restaurant(num,nom,img,descrip));
        }

        return lesRestaus;
    }
}
