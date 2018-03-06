package com.bonapp.ujm.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by maham on 06/03/2018.
 */

public class RepoMenu extends BaseDonnees {
    public static final String TABLE_NAME = "menu";
    public static final String KEY = "id";
    public static final String NOM = "nom";
    public static final String ENTREE = "id_entree";
    public static final String CHAUD = "id_chaud";
    public static final String DESSERT = "id_dessert";
    public static final String PRIX = "prix";
    public static final String DESCRIP = "description";
    public static final String RESTAU = "id_restau";

    public Context context;

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME +
            " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOM + " CHAR(60), " + ENTREE + " INTEGER, " + CHAUD + " INTEGER, "+
            DESSERT +" INTEGER,"+ PRIX + " REAL, " + DESCRIP + " TEXT" + RESTAU + " INTEGER);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public RepoMenu(Context context) {

        super(context, TABLE_CREATE);
        super.onCreate(DB);
        this.context = context;
    }

    public void ajouter(Menu m){
        ContentValues contVal = new ContentValues();

        contVal.put(NOM, m.getNom());
        contVal.put(ENTREE, m.getEntree().getId());
        contVal.put(CHAUD, m.getPlat_chaud().getId());
        contVal.put(DESSERT, m.getDessert().getId());
        contVal.put(PRIX, m.getPrix());
        contVal.put(DESCRIP, m.getDescription());
        contVal.put(RESTAU, m.getId_restau());

        DB.insert(TABLE_NAME,null,contVal);
    }

    public void supprimer(int id){
        DB.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    public void modifier(Menu m){
        ContentValues contVal = new ContentValues();

        contVal.put(NOM, m.getNom());
        contVal.put(ENTREE, m.getEntree().getId());
        contVal.put(CHAUD, m.getPlat_chaud().getId());
        contVal.put(DESSERT, m.getDessert().getId());
        contVal.put(PRIX, m.getPrix());
        contVal.put(DESCRIP, m.getDescription());
        contVal.put(RESTAU, m.getId_restau());

        DB.update(TABLE_NAME, contVal, KEY  + " = ?", new String[] {String.valueOf(m.getId())});

    }

    public ArrayList<Menu> selectionner(long id){
        Cursor c = DB.rawQuery("SELECT "+ KEY +", "+ NOM +", "+ ENTREE +", "+CHAUD+", "+DESSERT+", "+ PRIX +", "+ DESCRIP +
                " FROM "+ TABLE_NAME +"where id_restau = ?", new String[]{""+id} );

        ArrayList<Menu> lesMenus = new ArrayList<>();
        RepoPlat repoPlat = new RepoPlat(context);

        while(c.moveToNext()){
            int num = c.getInt(0);
            String nom = c.getString(1);
            int id_entree = c.getInt(2);
            int id_chaud = c.getInt(3);
            int id_dessert = c.getInt(4);
            float prix = c.getFloat(5);
            String descrip = c.getString(6);

            Plat entree = repoPlat.selectionnerPlat(id_entree);
            Plat chaud = repoPlat.selectionnerPlat(id_chaud);
            Plat dessert = repoPlat.selectionnerPlat(id_dessert);

            lesMenus.add(new Menu(num,nom,prix,entree,chaud,dessert,descrip));
        }

        return lesMenus;
    }
}
