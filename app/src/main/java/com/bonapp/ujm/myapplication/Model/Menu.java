package com.bonapp.ujm.myapplication.Model;

/**
 * Created by maham on 24/02/2018.
 */

public class Menu {
    public int id;


    public String nom;
    public int prix;
    public Plat entre;
    public Plat plat_chaud;
    public Plat dessert;
    public String description;

    public Menu(String nom, int prix, Plat entre, Plat plat_chaud, Plat dessert, String description) {
        this.nom = nom;
        this.prix = prix;
        this.entre = entre;
        this.plat_chaud = plat_chaud;
        this.dessert = dessert;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Plat getEntre() {
        return entre;
    }

    public void setEntre(Plat entre) {
        this.entre = entre;
    }

    public Plat getPlat_chaud() {
        return plat_chaud;
    }

    public void setPlat_chaud(Plat plat_chaud) {
        this.plat_chaud = plat_chaud;
    }

    public Plat getDessert() {
        return dessert;
    }

    public void setDessert(Plat dessert) {
        this.dessert = dessert;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
