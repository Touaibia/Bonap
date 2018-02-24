package com.bonapp.ujm.myapplication.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Smaïl on 23/02/2018.
 */

public class Databasehelper extends SQLiteOpenHelper{

    SQLiteDatabase db;

    private  static  final int DATABASE_VERSION = 1;
    private  static  final String DATABASE_NAME = "bonapp.db";
    private  static  final String TABLE_NAME = "contacts";
    private  static  final String COLUMN_ID ="id";
    private  static  final String COLUMN_TYPE ="type";
    private  static  final String COLUMN_USERNAME ="username";
    private  static  final String COLUMN_EMAIL = "email";
    private  static  final String COLUMN_PASSWORD ="password";
    private  static  final String COLUMN_ADRESS = "adress";
    private  static  final String COLUMN_CITY ="ville";
    private  static  final String COLUMN_TELEPHONE ="telephone";

    private  static  final String TABLE_CREATE = "create table contacts(id integer primary key not null ,"+"type text not null , username text not null , email text not null , password text not null , adress text not null , ville text not null , telephone text not null";


    public Databasehelper(Context context) {

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public String search(String identif){
        db = this.getReadableDatabase();
        String query = " select type, username, password from"+TABLE_NAME;
        Cursor curseur = db.rawQuery(query,null);
        String mdp,ident,type;
        mdp = "Not Found";
        if(curseur.moveToFirst()){
            do {

                    ident = curseur.getString(1);


                    if(ident.equals(identif)){
                        mdp = curseur.getString(2);
                        //a voir type = curseur.getString(0);
                        break;

                    }

            }while (curseur.moveToNext());
        }
        return mdp;
    }

    public void insertContact(Contact conctact){

        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE,conctact.getType());
        values.put(COLUMN_USERNAME,conctact.getUsername());
        values.put(COLUMN_PASSWORD,conctact.getPassword());
        values.put(COLUMN_EMAIL,conctact.getEmail());
        values.put(COLUMN_ADRESS,conctact.getAdress());
        values.put(COLUMN_CITY,conctact.getCity());
        values.put(COLUMN_TELEPHONE,conctact.getPhone());

        db.insert(TABLE_NAME,null,values);
        db.close();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String query = "DROP TABLE IF EXISTS"+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }
}
