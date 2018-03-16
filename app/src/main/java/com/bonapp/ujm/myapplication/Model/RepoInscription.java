package com.bonapp.ujm.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

/**
 * Created by Nianfo on 04/03/2018.
 */

public class RepoInscription {

    public BaseDonnees db;
    Context context;
    private  static  final String TABLE_NAME = "contacts";
    private  static  final String COLUMN_ID ="id";
    private  static  final String COLUMN_TYPE ="type";
    private  static  final String COLUMN_USERNAME ="username";
    private  static  final String COLUMN_EMAIL = "email";
    private  static  final String COLUMN_PASSWORD ="password";
   // private  static  final String COLUMN_ADRESS = "adress";
   // private  static  final String COLUMN_CITY ="ville";
    private  static  final String COLUMN_TELEPHONE ="telephone";
    private  static  final String COLUMN_NUMERORUE="numrue";
    private  static  final String COLUMN_NOMRUE = "nomrue";
    private  static  final String COLUMN_CODEPOSTAL = "codepostal";

    private  static  final String TABLE_CREATE =
            "create table contacts(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username TEXT, " +
                    "email TEXT, " +
                    "password TEXT, " +
                    "adresse INTEGER, " +
                    "telephone TEXT;";



    public RepoInscription(Context context){
        Toast.makeText(context,"ouverure ok",Toast.LENGTH_LONG).show();
       db = new BaseDonnees(context, TABLE_CREATE,TABLE_NAME);
       this.context = context;
       db.open();

   }
    public boolean identification(String username, String pwd){
        Cursor cursor = db.DB.rawQuery("select * from contacts " +
                "where username = "+username+" and password = "+pwd,null);

        if (cursor.moveToNext()){
            return true;
        }
        return false;
    }




    public long insertContact(Client conctact){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,conctact.getUsername());
        values.put(COLUMN_EMAIL,conctact.getEmail());
        values.put(COLUMN_PASSWORD,conctact.getPassword());
        values.put(COLUMN_NUMERORUE,conctact.getAdresse().getNumero());
        values.put("adresse",conctact.getAdresse().getId());
        values.put(COLUMN_TELEPHONE,conctact.getPhone());

        if(db.DB.insert(TABLE_NAME,null,values)==-1){

            Toast.makeText(context,"echec d'insertion",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context,"insertion ok",Toast.LENGTH_LONG).show();
        }
        return db.DB.insert(TABLE_NAME,null,values);
    }

    public void close(){db.close();}

}
