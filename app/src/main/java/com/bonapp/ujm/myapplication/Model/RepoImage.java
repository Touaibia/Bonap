package com.bonapp.ujm.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by maham on 13/03/2018.
 */

public class RepoImage extends BaseDonnees {

    public static final String TABLE_NAME = "image";
    public static final String KEY = "id";
    public static final String CONT = "bitmap";
    public static final String PARENT = "id_parent";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME +
            " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CONT + " BLOB, "+ PARENT +" INTEGER);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public Context context;


    public RepoImage(Context context) {
        super(context);
        this.context = context;
    }

    public long ajouter(Image im){
        ContentValues contVal = new ContentValues();

//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        im.getBitmap().compress(Bitmap.CompressFormat.PNG, 0, stream);
//
//        contVal.put(CONT, stream.toByteArray());
        contVal.put(PARENT, im.getId_parent());

        return DB.insert(TABLE_NAME,null,contVal);
    }

    public void modifier(Image im){
        ContentValues contVal = new ContentValues();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        im.getBitmap().compress(Bitmap.CompressFormat.PNG, 0, stream);

        contVal.put(CONT, stream.toByteArray());

        DB.update(TABLE_NAME, contVal, KEY  + " = ?", new String[] {String.valueOf(im.getId())});
    }

    public int supprimer(long id){
        return DB.delete(TABLE_NAME, KEY + " = ? ", new String[] {String.valueOf(id)});
    }

    public ArrayList<Image> selectAll(){
        Cursor c = DB.rawQuery("SELECT  id, bitmap, id_parent"+
                " FROM image", null);

        Log.d("Quelencjc", "je suis dedans");

        ArrayList<Image> images = new ArrayList<>();

        while (c.moveToNext()){
            Log.d("Quelencjc", "hjdsjjhdsjdsjkjksdkjd");
            long id  = c.getLong(0);
            byte[] img = c.getBlob(1);
            long par = c.getLong(2);
            Bitmap bitmap = null; //= BitmapFactory.decodeByteArray(img, 0, img.length);

            images.add(new Image(id,bitmap,par));
        }

        return images;
    }

    public Image selectionner(long id_parent){
        Cursor c = DB.rawQuery("SELECT "+ KEY +", "+ CONT +
                " FROM "+ TABLE_NAME +" where id_parent = ?", new String[]{""+id_parent} );

        Image image = new Image();

        if (c.moveToNext()){
            long id = c.getLong(0);
            byte[] img = c.getBlob(1);
//            Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);

            image.setId(id);
//            image.setBitmap(bitmap);
        }


        return image;
    }

}
