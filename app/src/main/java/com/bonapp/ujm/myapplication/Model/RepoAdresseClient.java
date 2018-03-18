package com.bonapp.ujm.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nianfo on 18/03/2018.
 */

public class RepoAdresseClient extends BaseDonnees{

        public static final String TABLE_NAME = "adresseClient";
        public static final String KEY = "id";
        public static final String NUM = "numero";
        public static final String TYPE = "type";
        public static final String INTITULE = "intitule";
        public static final String CODE = "code_post";
        public static final String ID_CLIENT = "id_cleint";

        public static final String TABLE_CREATE_CLIENT = "CREATE TABLE " + TABLE_NAME +
                "(" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ NUM + " CHAR(10), "+ TYPE + " CHAR(20), "+
                INTITULE +" CHAR(50), "+ CODE +" INTEGER, " + ID_CLIENT + " INTEGER);";

        public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

        public RepoAdresseClient(Context context) {
            super(context);
        }

        public void ajouter(Adresse ad){
            ContentValues contVal = new ContentValues();

            contVal.put(NUM, ad.getNumero());
            contVal.put(TYPE, ad.getType_voie());
            contVal.put(INTITULE, ad.getIntitule());
            contVal.put(CODE, ad.getCode_postal());
            contVal.put(ID_CLIENT, ad.getId_retau());

            DB.insert(TABLE_NAME,null,contVal);
        }

        public void supprimer(long id){
            DB.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
        }

        public void modifier(Adresse ad){
            ContentValues contVal = new ContentValues();

            contVal.put(NUM, ad.getNumero());
            contVal.put(TYPE, ad.getType_voie());
            contVal.put(INTITULE, ad.getIntitule());
            contVal.put(CODE, ad.getCode_postal());
            contVal.put(ID_CLIENT, ad.getId_retau());

            DB.update(TABLE_NAME, contVal, KEY  + " = ?", new String[] {String.valueOf(ad.getId())});

        }

        //Selectionner un Adresse
        public Adresse selectionner(long id){
            Cursor c = DB.rawQuery("SELECT "+ KEY +", "+ NUM +", "+ TYPE +", "+ INTITULE +", "+ CODE +
                    " FROM "+ TABLE_NAME +" where "+ID_CLIENT+" = ?", new String[]{""+id} );

            c.moveToNext();

            int idd = c.getInt(0);
            String num = c.getString(1);
            String type = c.getString(2);
            String inti = c.getString(3);
            int code = c.getInt(4);

            Adresse ad = new Adresse(idd,num,type,inti,code) ;


            return ad;
        }

        public List<Adresse> plusProcheRestoAdresse(){
            List<Adresse> list = new ArrayList<>();
            Cursor cursor = DB.rawQuery("select* from "+TABLE_NAME,null);
            while (cursor.moveToNext()){
                list.add(new Adresse(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5)
                ));
            }
            return list;
        }




}
