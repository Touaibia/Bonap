package com.bonapp.ujm.myapplication.Model;

import android.content.Context;
import android.media.Image;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.bonapp.ujm.myapplication.Controller.fait_la_commande;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * Created by Nianfo on 21/01/2018.
 */

public class Restaurant{
    public int id;
    public String nom;
    public String email;
    public String mot_passe;
    public Adresse adresse;
    public String tel;
    public String description;
    public int image;
    public ArrayList<TypeCuisine> typeCuisines=  new ArrayList<>();
    public ArrayList<Plat> plats=new ArrayList<>();
    public ArrayList<Plat> entrees =  new ArrayList<>();
    public ArrayList<Plat> desserts =  new ArrayList<>();


    public Restaurant() {
    }

    public Restaurant(String nom, String email, String mot_passe, Adresse adresse, String tel) {
        this.nom = nom;
        this.email = email;
        this.mot_passe = mot_passe;
        this.adresse = adresse;
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMot_passe() {
        return mot_passe;
    }

    public void setMot_passe(String mot_passe) {
        this.mot_passe = mot_passe;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public ArrayList<TypeCuisine> getTypeCuisines() {
        return typeCuisines;
    }

    public void setTypeCuisines(ArrayList<TypeCuisine> typeCuisines) {
        this.typeCuisines = typeCuisines;
    }



    public ArrayList<Plat> getPlats() {
        return plats;
    }

    public void setPlats(ArrayList<Plat> plats) {
        this.plats = plats;
    }

    public ArrayList<Plat> getEntrees() {
        return entrees;
    }

    public void setEntrees(ArrayList<Plat> entrees) {
        this.entrees = entrees;
    }

    public ArrayList<Plat> getDesserts() {
        return desserts;
    }

    public void setDesserts(ArrayList<Plat> desserts) {
        this.desserts = desserts;
    }

    public void ajoutePlat(int image, String nom, int prix,String description, String type ) {
        this.plats.add(new Plat(image,nom,prix,description,type));
    }

    public void ajouteEntree(int image, String nom, int prix,String description, String type) {
        this.entrees.add(new Plat(image,nom,prix,description,type));
    }

    public void ajouteDessert(int image, String nom, int prix,String description, String type) {
        this.desserts.add(new Plat(image,nom,prix,description,type));
    }
}
