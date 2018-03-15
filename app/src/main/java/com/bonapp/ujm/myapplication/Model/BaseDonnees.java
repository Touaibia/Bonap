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
    private static final int VERSION =19;
    private String creerTable;
    public String tableName;
    private  static  final String TABLE_CREATE =
            "create table contacts(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username TEXT, " +
                    "email TEXT, " +
                    "password TEXT," +
                    "telephone INTEGER);";
    private static final String TABLE_RESATURANT = "create table restaurant("+
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nom TEXT, " +
                    "email TEXT, " +
                    "password TEXT, " +
                    "telephone TEXT, "+
                    "description TEXT)";

    private static final String TABLE_ADRESSE = "create table adresse("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "numero TEXT, "+
            "type TEXT, "+
            "intitule TEXT, "+
            "code_post INTEGER, "+
            "id_restau INTEGER)";

    private static final String TABLE_PLAT = "create table plat("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "nom TEXT, "+
            "type INTEGER, "+
            "description TEXT, "+
            "prix REAL, "+
            "id_restau INTEGER)";

    private static final String TABLE_TYPE_CUISINE ="create table typeCuisine("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "nom TEXT, "+
            "image INTEGER, "+
            "description TEXT)";

    private static final String TABLE_TYPECUISINE_RESTAU = "create table typeCuisineRestaurant("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "id_type INTEGER, "+
            "id_restau INTEGER)";

    public static final String TABLE_IMAGE = "CREATE TABLE image(" +
             "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
             "bitmap  BLOB, "+
             "id_parent INTEGER);";

    public BaseDonnees(Context context) {
        super(context, DATA_BASE_NAME, null, VERSION);
//        this.creerTable = creerTable;
//        this.tableName = tableName;
       //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
          sqLiteDatabase.execSQL(TABLE_RESATURANT);
          sqLiteDatabase.execSQL(TABLE_ADRESSE);
          sqLiteDatabase.execSQL(TABLE_CREATE);
          sqLiteDatabase.execSQL(TABLE_PLAT);
          sqLiteDatabase.execSQL(TABLE_TYPE_CUISINE);
          sqLiteDatabase.execSQL(TABLE_TYPECUISINE_RESTAU);
          sqLiteDatabase.execSQL(TABLE_IMAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+tableName);
            onCreate(sqLiteDatabase);
    }

    public void open(){
        DB = this.getWritableDatabase();
    }
    public void close(){
        DB.close();
    }

}
