package com.bonapp.ujm.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by maham on 07/03/2018.
 */

public class RepoTypeCuisineRestaurant extends BaseDonnees {

    public static final String TABLE_NAME = "typeCuisineRestaurant";
    public static final String KEY = "id";
    public static final String TYPE = "id_type";
    public static final String RESTAU = "id_restau";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME +
            " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TYPE + " INTEGER, " + RESTAU + " INTEGER);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";


    public Context context;

    public RepoTypeCuisineRestaurant(Context context) {
        super(context);
        this.context = context;
    }

    public long ajouter(TypeCuisineRestaurant tpr){
        ContentValues contVal = new ContentValues();

        contVal.put(TYPE, tpr.getId_type());
        contVal.put(RESTAU, tpr.getId_restau());


        return DB.insert(TABLE_NAME,null,contVal);
    }

    public int  supprimer(long id_type, long id_restau){
        return DB.delete(TABLE_NAME, TYPE + " = ? and "+ RESTAU +" = ?", new String[] {String.valueOf(id_type),""+id_restau});
    }

    public void modifier(TypeCuisineRestaurant tpr){
        ContentValues contVal = new ContentValues();

        contVal.put(RESTAU, tpr.getId_restau());
        contVal.put(TYPE, tpr.getId_type());

        DB.update(TABLE_NAME, contVal, KEY  + " = ?", new String[] {String.valueOf(tpr.getId())});

    }

    public ArrayList<TypeCuisineRestaurant> selectAll(){

        Cursor c = DB.rawQuery("SELECT "+ KEY +", "+TYPE+ ", "+ RESTAU +
                 " FROM "+ TABLE_NAME , new String[]{} );

        ArrayList<TypeCuisineRestaurant> lesTypes = new ArrayList<>();

        while(c.moveToNext()){
            long id = c.getLong(0);
            long id_type = c.getLong(1);
            long id_restau = c.getLong(2);

            lesTypes.add(new TypeCuisineRestaurant(id, id_type, id_restau));
        }

        return lesTypes;

    }

    //Selection des Restaurants en fonction de type de cuisine
    public ArrayList<Restaurant> selectionnerRestau(long id_type){
        Cursor c = DB.rawQuery("SELECT "+ KEY +", "+ RESTAU +
                " FROM "+ TABLE_NAME +" where id_type = ?", new String[]{""+id_type} );

        ArrayList<Restaurant> lesRestaus = new ArrayList<>();
        RepoRestaurant repoRestaurant = new RepoRestaurant(context);

        repoRestaurant.open();

        while(c.moveToNext()){
            long num = c.getInt(1);

            lesRestaus.add(repoRestaurant.selectionnerAccueil(1));
        }

        repoRestaurant.close();

        return lesRestaus;
    }

    //Selection des types de cuisine d'un restaurant
    public ArrayList<TypeCuisine> selectionnerType(long id_restau){

        Log.d("ID_Restau reçu ", id_restau+"");

        Cursor c = DB.rawQuery("SELECT "+ KEY +", "+ TYPE +
                " FROM "+ TABLE_NAME +" where id_restau = ?", new String[]{""+id_restau} );

        ArrayList<TypeCuisine> lesTypes = new ArrayList<>();
        RepoTypeCuisine repoTypeCuisine = new RepoTypeCuisine(context);
        repoTypeCuisine.open();
        int i =0;
        while(c.moveToNext()){
            i++;

            long id_type = c.getLong(1);

            Log.d(i+"eme type ",id_type+"");

            lesTypes.add(repoTypeCuisine.selectionner(id_type));
        }

        repoTypeCuisine.close();

        return lesTypes;
    }
}
