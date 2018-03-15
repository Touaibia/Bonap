package com.bonapp.ujm.myapplication.Model;

import android.graphics.Bitmap;

/**
 * Created by maham on 13/03/2018.
 */

public class Image {
    long id;
    Bitmap bitmap;
    long id_parent;

    public Image(long id, Bitmap bit, long id_parent) {
        this.id = id;
        this.bitmap = bit;
        this.id_parent = id_parent;
    }

    public Image(Bitmap bit, long id_parent) {
        this.bitmap = bit;
        this.id_parent = id_parent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setCont(Bitmap cont) {
        this.bitmap = cont;
    }

    public long getId_parent() {
        return id_parent;
    }

    public void setId_parent(long id_parent) {
        this.id_parent = id_parent;
    }
}
