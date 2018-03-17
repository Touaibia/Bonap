package com.bonapp.ujm.myapplication.Model;

/**
 * Created by maham on 24/02/2018.
 */

public class TypeCuisine {
    public long id;
    public String nom;
    public String description;
    public int image;

    public TypeCuisine(String nom, int image, String description) {
        this.nom = nom;
        this.description = description;
        this.image = image;
    }

    public TypeCuisine(long id, String nom,int image, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
