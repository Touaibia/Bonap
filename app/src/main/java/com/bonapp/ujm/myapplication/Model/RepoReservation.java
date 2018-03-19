package com.bonapp.ujm.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.bonapp.ujm.myapplication.Model.RepoPlat.PRIX;

/**
 * Created by maham on 05/03/2018.
 */

public class RepoReservation extends BaseDonnees {
    Context context;
    public static final String TABLE_NAME = "reservation";
    public static final String KEY = "id";
    public static final String NB_PERS = "nb_personnes";
    public static final String SERVICE = "service";
    public static final String DATE = "date";
    public static final String HEURE = "heure";
    public static final String RESTAU = "id_restau";
    public static final String CLIENT = "id_client";

    public static final String CREATE_RESERVATION = "CREATE TABLE " + TABLE_NAME +
            "(" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NB_PERS + " INTEGER, " + SERVICE + " INTEGER, " + DATE + " TEXT, "+
            HEURE + " CHAR(5), " + RESTAU + " INTEGER, "+ CLIENT + " INTEGER);";

    public RepoReservation(Context context) {
        super(context);
        context = context;
        this.tableName = TABLE_NAME;
    }

    public long ajouter(Reservation res, String date){
        ContentValues contVal = new ContentValues();

        contVal.put(NB_PERS, res.getNb_personnes());
        contVal.put(SERVICE, res.getService());
        contVal.put(DATE, date);
        contVal.put(HEURE, res.getHeure());
        contVal.put(RESTAU, res.getId_restau());
        contVal.put(CLIENT, res.getId_client());

        long id = DB.insert(TABLE_NAME,null,contVal);
       // Toast.makeText(context,"reservation :"+id,Toast.LENGTH_LONG).show();
        return id;
    }

    public void supprimer(int id){
        DB.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    public void modifier(Reservation res){
        ContentValues contVal = new ContentValues();

        contVal.put(NB_PERS, res.getNb_personnes());
        contVal.put(SERVICE, res.getService());
        contVal.put(DATE, String.valueOf(res.getDate()));
        contVal.put(HEURE, res.getHeure());
        contVal.put(RESTAU, res.getId_restau());
        contVal.put(CLIENT, res.getId_client());

        DB.update(TABLE_NAME, contVal, KEY  + " = ?", new String[] {String.valueOf(res.getId())});
    }

    public ArrayList<Reservation> selectionRestau(long id_restau, String date){
        Cursor c = DB.rawQuery("SELECT "+ KEY +", "+ NB_PERS +", "+ SERVICE +", "+ HEURE +", "+ CLIENT +
                " FROM "+ TABLE_NAME +" where date = ? AND id_restau = ?", new String[]{date,""+id_restau });

        ArrayList<Reservation> lesReserv = new ArrayList<>();

        while(c.moveToNext()){
            int id = c.getInt(0);
            int nb_pers = c.getInt(1);
            int serv = c.getInt(2);
            String heure = c.getString(3);
            long cl_id = c.getInt(4);

            RepoInscription repoInscription = new RepoInscription(context);
            repoInscription.open();

            lesReserv.add(new Reservation(id,nb_pers,serv,heure,repoInscription.getClient(cl_id)));
        }
        return lesReserv;
    }

    public ArrayList<Reservation> selectionClient(int id_client) throws ParseException {
//       Toast.makeText(context," okknn ",Toast.LENGTH_LONG).show();
        Cursor c = DB.rawQuery("SELECT "+ KEY +", "+ NB_PERS +", "+ SERVICE +", "+ HEURE +", "+ DATE +", "+RESTAU+
                " FROM "+ TABLE_NAME+" where "+CLIENT+ " = ?", new String[]{""+id_client} );

        ArrayList<Reservation> lesReserv = new ArrayList<>();

        while(c.moveToNext()){
            int id = c.getInt(0);
            int nb_pers = c.getInt(1);
            int serv = c.getInt(2);
            String heure = c.getString(3);
            String dateStr = c.getString(4);
            int restau_id = c.getInt(5);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            date =  dateFormat.parse(dateStr);
            lesReserv.add(new Reservation(id,nb_pers,serv,date,heure,restau_id));
        }
        return lesReserv;
    }
}
