package com.bonapp.ujm.myapplication.Model;


import android.widget.Spinner;

/**
 * Created by maham on 24/02/2018.
 */

public class Plat {
    public int id;
    public int image;
    public String nom;
    public int prix;
    public String description;
    public String type;
    public Spinner nb_plats;

    public Plat(int image, String nom, int prix, String description,String type) {
        this.image = image;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.type = type;
    }

    public int getImage() {
        return image;
    }

    public String getNom() {
        return nom;
    }

    public int getPrix() {
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

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }
}
