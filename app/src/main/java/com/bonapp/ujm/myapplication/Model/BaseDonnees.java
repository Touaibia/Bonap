package com.bonapp.ujm.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Nianfo on 23/02/2018.
 */

public class BaseDonnees extends SQLiteOpenHelper {
    SQLiteDatabase DB ;
    public static final String DATA_BASE_NAME = "bonapp.db ";
    public static final String RESTAURANT_TABLE_NAME = "restaurant ";
    public static final int VERSION = 1;
    // table restaurant
    public static final String ID = "ID INTEGER PRIMARY KEY AUTOINCREMENT ";
    public static final String NAME = "NAME TEXT ";
    public static final String IMAGE = "IMAGE INTEGER ";


    public BaseDonnees(Context context) {
        super(context, DATA_BASE_NAME, null, VERSION);
        DB = this.getWritableDatabase();
        Toast.makeText(context,"base ok",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table essai( id INTEGER, nom TEXT );");
        /*sqLiteDatabase.execSQL("create table "+RESTAURANT_TABLE_NAME
                +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "NAME TEXT,"
                + "IMAGE INTEGER )");*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+RESTAURANT_TABLE_NAME);
            onCreate(sqLiteDatabase);
    }

    public void open(){
        DB = this.getWritableDatabase();
    }
    public void close(){
        DB.close();
    }
    public boolean insertResto(String nom,String descript, String img){

        ContentValues contentValues = new ContentValues();
        contentValues.put("id",nom);
        contentValues.put("nom",img);
        if(DB.insert("essai",null,contentValues)==-1){

        return false;
        }
        else return true;
    }

    public Cursor getAllResto(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from "+RESTAURANT_TABLE_NAME,null);
        return cursor;
    }
}
