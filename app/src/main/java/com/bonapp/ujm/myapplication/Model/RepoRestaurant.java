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

public class RepoRestaurant {
    //BaseDonnees db;
    Context context;
    String TABLE = "restaurant";
    public BaseDonnees db;
    private String TABLE_CREATE =
            "CREATE TABLE  restaurant("+
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nom TEXT, " +
                    "email TEXT, " +
                    "password TEXT, " +
                    "adresse INTEGER, " +
                    "telephone TEXT);";


    public RepoRestaurant(Context context){
       //Toast.makeText(context,"ouverure ok",Toast.LENGTH_LONG).show();
        db = new BaseDonnees(context, TABLE_CREATE,"restaurant");
        //db.DB.execSQL(TABLE_CREATE);
        this.context = context;
        this.db = db;
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
        db.DB.insert("restaurant",null,contentValues);

    }

    public String getLeResto(int id){
        Cursor cursor = db.DB.rawQuery("select* from restaurant where id="+id,null);
        List idt = new ArrayList();
        cursor.moveToNext();
        return cursor.getString(1);
    }
    public void close(){db.close();}
}
