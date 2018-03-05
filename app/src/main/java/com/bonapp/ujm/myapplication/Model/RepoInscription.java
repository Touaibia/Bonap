package com.bonapp.ujm.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.bonapp.ujm.myapplication.Controller.Client;


/**
 * Created by Nianfo on 04/03/2018.
 */

public class RepoInscription {

    BaseDonnees db;
    Context context;
    private  static  final String TABLE_NAME = "contacts";
    private  static  final String COLUMN_ID ="id";

    private  static  final String COLUMN_TYPE ="type";
    private  static  final String COLUMN_USERNAME ="username";
    private  static  final String COLUMN_EMAIL = "email";
    private  static  final String COLUMN_PASSWORD ="password";
    private  static  final String COLUMN_ADRESS = "adress";
    private  static  final String COLUMN_CITY ="ville";
    private  static  final String COLUMN_TELEPHONE ="telephone";
    private  static  final String COLUMN_TYPEVOIX ="typevoix";
    private  static  final String COLUMN_NUMERORUE ="numrue";
    private  static  final String COLUMN_NOMRUE ="nomrue";
    private  static  final String COLUMN_CODEPOSTALE ="codepostal";
    private  static  final String TABLE_CREATE =
            "create table contacts(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "type TEXT, " +
                    "username TEXT, " +
                    "email TEXT, " +
                    "password TEXT, " +
                   // "numrue TEXT, "+
                   // "typevoix TEXT, "+
                   // "nomrue TEXT, "+
                   // "codepostal TEXT, "+
                    "adress TEXT, " +
                    "ville TEXT, " +
                    "telephone TEXT)";


    public RepoInscription(Context context){
        Toast.makeText(context,"ouverure ok",Toast.LENGTH_LONG).show();
       db = new BaseDonnees(context, TABLE_CREATE);
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




    public void insertContact(Client conctact){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE,conctact.getType());
        values.put(COLUMN_USERNAME,conctact.getUsername());
        values.put(COLUMN_EMAIL,conctact.getEmail());
        values.put(COLUMN_PASSWORD,conctact.getPassword());
        values.put(COLUMN_ADRESS,conctact.getAdress());
        values.put(COLUMN_CITY,conctact.getCity());
        values.put(COLUMN_TELEPHONE,conctact.getPhone());


        if(db.DB.insert(TABLE_NAME,null,values)==-1){

            Toast.makeText(context,"echec d'insertion",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context,"insertion ok",Toast.LENGTH_LONG).show();
        }

    }

    public void insertContactRestaurateur(Restaurant conctact){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE,conctact.getType());
        values.put(COLUMN_USERNAME,conctact.getNom());
        values.put(COLUMN_EMAIL,conctact.getEmail());
        values.put(COLUMN_PASSWORD,conctact.getMot_passe());


        values.put(COLUMN_NUMERORUE,conctact.getAdresse().getNumero());
        values.put(COLUMN_TYPEVOIX,conctact.getAdresse().getType_voie());
        values.put(COLUMN_NOMRUE,conctact.getAdresse().getIntitule());
        values.put(COLUMN_CODEPOSTALE,conctact.getAdresse().getCode_postal());
        values.put(COLUMN_TELEPHONE,conctact.getTel());



        if(db.DB.insert(TABLE_NAME,null,values)==-1){

            Toast.makeText(context,"echec d'insertion",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context,"insertion ok",Toast.LENGTH_LONG).show();
        }

    }




    public void close(){db.close();}

}
