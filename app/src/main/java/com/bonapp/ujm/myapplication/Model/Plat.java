package com.bonapp.ujm.myapplication.Model;


import android.widget.Spinner;

/**
 * Created by maham on 24/02/2018.
 */

public class Plat {
    public long id;
    public int image;
    public String nom;
    public float prix;
    public String description;
    public int type;
    long id_restau;
    public Spinner nb_plats;


    public Plat(long id, int image, String nom, float prix, String description) {
        this.id = id;
        this.image = image;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
    }

    public Plat(String nom, int image, float prix, String description, int type, long id_restau) {
        this.image = image;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.type = type;
        this.id_restau=id_restau;
    }

    public int getImage() {
        return image;
    }

    public String getNom() {
        return nom;
    }

    public float getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public long getId_restau() {
        return id_restau;
    }

    public void setId_restau(long id_restau) {
        this.id_restau = id_restau;
    }
}
