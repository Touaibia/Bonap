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
    public static final int VERSION = 1;
    private String creerTable;


    public BaseDonnees(Context context, String creerTable) {
        super(context, DATA_BASE_NAME, null, VERSION);
        this.creerTable = creerTable;
        //SQLiteDatabase db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(this.creerTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+this.creerTable);
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
        contentValues.put("",nom);
       // contentValues.put(IMAGE,img);
        if(DB.insert("essai",null,contentValues)==-1){

        return false;
        }
        else return true;
    }

    /*public Cursor getAllResto(){
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor cursor = DB.rawQuery("select * from "+RESTAURANT_TABLE_NAME,null);
        return cursor;
    }*/
}
