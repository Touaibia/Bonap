package com.bonapp.ujm.myapplication.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bonapp.ujm.myapplication.Model.Client;

/**
 * Created by Smaïl on 23/02/2018.
 */

public class Databasehelper extends SQLiteOpenHelper{

    SQLiteDatabase db;

    private  static  final int DATABASE_VERSION = 1;
    private  static  final String DATABASE_NAME = "bonapp.db";
    private  static  final String TABLE_NAME_CLIENT = "clients";
    private  static  final String TABLE_NAME_RESTAURANT = "restaurants";

    private  static  final String COLUMN_ID ="id";
    private  static  final String COLUMN_TYPE ="type";
    private  static  final String COLUMN_USERNAME ="username";
    private  static  final String COLUMN_EMAIL = "email";
    private  static  final String COLUMN_PASSWORD ="password";
    private  static  final String COLUMN_ADRESS = "adress";
    private  static  final String COLUMN_CITY ="ville";
    private  static  final String COLUMN_TELEPHONE ="telephone";

    private  static  final String TABLE_CREATE_CLIENT = "create table clients(id integer primary key not null ,"+"type text not null , username text not null , email text not null , password text not null , adress text not null , ville text not null , telephone text not null";
    private  static  final String TABLE_CREATE_RESTAURANT = "create table restaurants(id integer primary key not null ,"+"type text not null , username text not null , email text not null , password text not null , adress text not null , ville text not null , telephone text not null";


    public Databasehelper(Context context) {

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_CLIENT);
        this.db = db;
    }

    public String searchClient(String identif){
        db = this.getReadableDatabase();
        String query = " select type, username, password from"+TABLE_NAME_CLIENT;
        Cursor curseur = db.rawQuery(query,null);
        String mdp,ident,type;
        mdp = "Not Found";
        if(curseur.moveToFirst()){
            do {

                    ident = curseur.getString(1);


                    if(ident.equals(identif)){
                        mdp = curseur.getString(2);

                        break;

                    }

            }while (curseur.moveToNext());
        }
        return mdp;
    }

    public String searchrRestaurant(String identif){
        db = this.getReadableDatabase();
        String query = " select type, username, password from"+TABLE_NAME_RESTAURANT;
        Cursor curseur = db.rawQuery(query,null);
        String mdp,ident,type;
        mdp = "Not Found";
        if(curseur.moveToFirst()){
            do {

                ident = curseur.getString(1);


                if(ident.equals(identif)){
                    mdp = curseur.getString(2);

                    break;

                }

            }while (curseur.moveToNext());
        }
        return mdp;
    }

    public void insertContact(Client conctact){

        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE,conctact.getType());
        values.put(COLUMN_USERNAME,conctact.getUsername());
        values.put(COLUMN_PASSWORD,conctact.getPassword());
        values.put(COLUMN_EMAIL,conctact.getEmail());
        values.put(COLUMN_ADRESS,conctact.getAdress());
        values.put(COLUMN_CITY,conctact.getCity());
        values.put(COLUMN_TELEPHONE,conctact.getPhone());

        db.insert(TABLE_NAME_CLIENT,null,values);
        db.close();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String query = "DROP TABLE IF EXISTS"+TABLE_NAME_CLIENT;
        db.execSQL(query);
        this.onCreate(db);

    }
}
