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
    public SQLiteDatabase DB ;
    public static final String DATA_BASE_NAME = "bonapp.db ";
    private static final int VERSION =6;
    private String creerTable;
    private String tableName;
   /* private String TABLE_CREATE = "create table clientResto(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "id_client TEXT, " +
                    "id_resto TEXT)";
    private String TABLE_CREATE1 = "create table restaurant("+
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nom TEXT, " +
                    "email TEXT, " +
                    "password TEXT, " +
                    "adresse INTEGER, " +
                    "telephone TEXT)";*/


    public BaseDonnees(Context context, String creerTable,String tableName) {
        super(context, DATA_BASE_NAME, null, VERSION);
        this.creerTable = creerTable;
        this.tableName = tableName;
       //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(this.creerTable);
        //sqLiteDatabase.execSQL(TABLE_CREATE);
        //sqLiteDatabase.execSQL(TABLE_CREATE1);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+tableName);
           // sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+"restaurant");
            onCreate(sqLiteDatabase);
    }

    public void open(){
        DB = this.getWritableDatabase();
    }
    public void close(){
        DB.close();
    }

}
