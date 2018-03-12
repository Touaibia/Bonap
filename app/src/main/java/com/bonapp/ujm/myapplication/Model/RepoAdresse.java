package com.bonapp.ujm.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by maham on 07/03/2018.
 */

public class RepoAdresse extends BaseDonnees {
    public static final String TABLE_NAME = "adresse";
    public static final String KEY = "id";
    public static final String NUM = "numero";
    public static final String TYPE = "type";
    public static final String INTITULE = "intitule";
    public static final String CODE = "code_post";
    public static final String RESTAU = "id_restau";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME +
            " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ NUM + "CHAR(10), "+ TYPE + " CHAR(20), "+
            INTITULE +" CHAR(50), "+ CODE +" INTEGER, " + RESTAU + " INTEGER);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public RepoAdresse(Context context) {
        super(context);
    }

    public void ajouter(Adresse ad){
        ContentValues contVal = new ContentValues();

        contVal.put(NUM, ad.getNumero());
        contVal.put(TYPE, ad.getType_voie());
        contVal.put(INTITULE, ad.getIntitule());
        contVal.put(CODE, ad.getCode_postal());
        contVal.put(RESTAU, ad.getId_retau());

        DB.insert(TABLE_NAME,null,contVal);
    }

    public void supprimer(long id){
        DB.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    public void modifier(Adresse ad){
        ContentValues contVal = new ContentValues();

        contVal.put(NUM, ad.getNumero());
        contVal.put(TYPE, ad.getType_voie());
        contVal.put(INTITULE, ad.getIntitule());
        contVal.put(CODE, ad.getCode_postal());
        contVal.put(RESTAU, ad.getId_retau());

        DB.update(TABLE_NAME, contVal, KEY  + " = ?", new String[] {String.valueOf(ad.getId())});

    }

    //Selectionner un Adresse
    public Adresse selectionner(long id){
        Cursor c = DB.rawQuery("SELECT "+ KEY +", "+ NUM +", "+ TYPE +", "+ INTITULE +", "+ CODE +
                " FROM "+ TABLE_NAME +" where id_restau = ?", new String[]{""+id} );

        c.moveToNext();

        int idd = c.getInt(0);
        String num = c.getString(1);
        String type = c.getString(2);
        String inti = c.getString(3);
        int code = c.getInt(4);

        Adresse ad = new Adresse(idd,num,type,inti,code) ;


        return ad;
    }

}
