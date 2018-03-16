package com.bonapp.ujm.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

/**
 * Created by Nianfo on 04/03/2018.
 */

public class RepoInscription extends BaseDonnees {

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

    private  static  final String TABLE_CONTACTE =
            "create table contacts(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username TEXT, " +
                    "email TEXT, " +
                    "password TEXT," +
                    "telephone INTEGER);";


    public RepoInscription(Context context) {
        super(context);
       // Toast.makeText(context, "ouverure ok", Toast.LENGTH_LONG).show();
        this.context = context;
       // this.tableName = "contacts";
    }
    public long identification(String email, String pwd){
        Cursor cursor = DB.rawQuery("select id from contacts " +
                "where password = ?",new String[]{""+pwd});

        if (cursor.moveToNext()){
            return cursor.getInt(0) ;
        }
        return -1;
    }




    public long insertContact(Client conctact){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,conctact.getUsername());
        values.put(COLUMN_EMAIL,conctact.getEmail());
        values.put(COLUMN_PASSWORD,conctact.getPassword());
        values.put(COLUMN_TELEPHONE,conctact.getPhone());

        return DB.insert(TABLE_NAME,null,values);
    }

    public void close(){DB.close();}

}
