package com.bonapp.ujm.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.bonapp.ujm.myapplication.Controller.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nianfo on 04/03/2018.
 */

public class RepoClientRestoFavori {
    BaseDonnees db;
    Context context;
    String TABLE_CREATE = "create table clientResto(id integer primary key autoincrement, " +
            "id_client text, " +
            "id_resto text);";

    public RepoClientRestoFavori(Context context){
        Toast.makeText(context,"ouverure ok",Toast.LENGTH_LONG).show();
        db = new BaseDonnees(context, TABLE_CREATE);
        this.context = context;
        db.open();

    }
    public void ajouteResto(int r, int c){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_client","rrrr");
        contentValues.put("id_resto","rrr");
       if( db.DB.insert("clientResto",null,contentValues)==-1){
           Toast.makeText(context,"echec d'insertion restau client",Toast.LENGTH_LONG).show();
       }else {
           Toast.makeText(context,"echec d'insertion restau client",Toast.LENGTH_LONG).show();
       }
    }
    public List<Restaurant> getAllResto(int c){
        Cursor cursor = db.DB.rawQuery("select* from restaurant" +
                " where id in (select id_resto from "+TABLE_CREATE+" where ic_client ="+c+")",null);
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
