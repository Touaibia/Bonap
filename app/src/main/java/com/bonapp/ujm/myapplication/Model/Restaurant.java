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

public class Restaurant {
    public String nom;
    public int image;
    public List<Plats> plats;
    public List<Plats> entrees;
    public List<Plats> desserts;
    public Restaurant(){
        plats = new ArrayList<>();
        entrees = new ArrayList<>();
        desserts = new ArrayList<>();
    }
    public Restaurant(String nom,int img){
        this.nom = nom;
        this.image = img;

    }

    public void ajoutePlat(String nom, int image) {
        this.plats.add(new Plats(nom,image));
    }
    public void ajouteEntree(String nom, int image) {
        this.entrees.add(new Plats(nom,image));
    }
    public void ajouteDessert(String nom, int image) {
        this.desserts.add(new Plats(nom,image));
    }
    public class Plats{
        public int image;
        public String nom;
        public Spinner nb_personnes ;

        public Plats(String nom,int image) {
            this.image = image;
            this.nom = nom;

        }
    }
}
