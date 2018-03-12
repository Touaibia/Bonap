package com.bonapp.ujm.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nianfo on 04/03/2018.
 */

public class RepoClientRestoFavori {
    //BaseDonnees db;
    Context context;
    BaseDonnees db;
    String TABLE_CREATE =
            "create table clientResto(" +
            "id integer primary key autoincrement, " +
            "id_client text, " +
            "id_resto text);";

    public RepoClientRestoFavori(Context context ){
    //Toast.makeText(context,"ouverure ok",Toast.LENGTH_LONG).show();
        db = new BaseDonnees(context);
        this.context = context;
        db.open();

    }
            public void ajouteResto(int r, int c){
                //String insert = "insert into clientResto(id_client,id_resto) values('ok','insertion')";
                ContentValues contentValues = new ContentValues();
                contentValues.put("id_client","rrggg");
                contentValues.put("id_resto","rrressai");

                db.DB.insert("clientResto",null,contentValues);
            }
            public List<Restaurant> getAllResto(int c){
                Cursor cursor = db.DB.rawQuery("select* from restaurant",null);
                // " where id in (select id_resto from clientResto where ic_client ="+c+")",null);
                List idt = new ArrayList();
                while(cursor.moveToNext()){
                    Cursor ad = db.DB.rawQuery("select* from adresse where id ="+cursor.getInt(4),null);
                    ad.moveToNext();
                    Adresse d = new Adresse(ad.getString(2),ad.getString(3),ad.getString(4),ad.getInt(5),ad.getLong(6));
//                    idt.add(new Restaurant(cursor.getString(2),cursor.getString(3),cursor.getString(4),d,
//                    cursor.getString(6)));
        }
    return idt;
    }

    public void close(){db.close();}
}
