package com.bonapp.ujm.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maham on 07/03/2018.
 */

public class RepoAdresse extends BaseDonnees {

    public static final String Table_create = "create table adresse(" +
            "id integer primary key autoincrement, " +
            "numero integer, " +
            "typevoie text, " +
            "intitule text, " +
            "codepostale text, " +
            "idresto integer);";
    public static final String table_name = "adresse";

    public RepoAdresse(Context context) {
        super(context, Table_create, table_name);
    }

    public void insertAdresse(Adresse ad,int id){
        ContentValues values = new ContentValues();
        values.put("numero",ad.getNumero());
        values.put("typevoie",ad.getType_voie());
        values.put("intitule",ad.getIntitule());
        values.put("codepostale",ad.getCode_postal());
        values.put("idresto",id);
        DB.insert(table_name,null,values);
    }

    public List<Adresse> getAdresseProche(){
        List <Adresse> list = new ArrayList<>();
        open();
        Cursor cursor = DB.rawQuery("select* from "+table_name,null);

        while(cursor.moveToNext()){
            //Adresse(String numero, String type_voie, String intitule, String code_postal)
            Adresse d = new Adresse(cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));
            d.setId_resto(cursor.getInt(5));
            list.add(d);
        }
    return list;
    }

}
