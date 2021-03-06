package com.bonapp.ujm.myapplication.Model;

/**
 * Created by maham on 24/02/2018.
 */

public class Menu {
    public int id;
    public String nom;
    public float prix;
    public Plat entree;
    public Plat plat_chaud;
    public Plat dessert;
    public String description;
    public int id_restau;

    public Menu(String nom, float prix, Plat entree, Plat plat_chaud, Plat dessert, String description, int id_restau) {
        this.nom = nom;
        this.prix = prix;
        this.entree = entree;
        this.plat_chaud = plat_chaud;
        this.dessert = dessert;
        this.description = description;
        this.id_restau = id_restau;
    }

    public Menu(int id, String nom, float prix, Plat entree, Plat plat_chaud, Plat dessert, String description) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.entree = entree;
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

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Plat getEntree() {
        return entree;
    }

    public void setEntree(Plat entre) {
        this.entree = entre;
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

    public int getId_restau() {
        return id_restau;
    }

    public void setId_restau(int id_restau) {
        this.id_restau = id_restau;
    }
}
