package com.bonapp.ujm.myapplication.Model;

import java.util.Date;

/**
 * Created by Nianfo on 08/02/2018.
 */

public class Reservation {

    long id;
    int nb_personnes;
    int service;
    Date date;
    String heure;
    int id_restau;
    int id_client;

    public Reservation() {
    }


    public Reservation(int nb_personnes, int service, Date date,String heure, int id_restau, int id_client) {
        this.nb_personnes = nb_personnes;
        this.service = service;
        this.date = date;
        this.heure = heure;
        this.id_restau = id_restau;
        this.id_client = id_client;
    }

    //Recuperer les Reserv depuis le compte Retaurant
    public Reservation(long id, int nb_personnes, int service, String heure, int id_client) {
        this.id = id;
        this.nb_personnes = nb_personnes;
        this.service = service;
        this.heure = heure;
        this.id_client = id_client;
    }

    //Recuperer les Reserv depuis le compte Cleint
    public Reservation(long id, int nb_personnes, int service, Date date,String heure, int id_restau) {
        this.id = id;
        this.nb_personnes = nb_personnes;
        this.service = service;
        this.date = date;
        this.heure = heure;
        this.id_restau = id_restau;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNb_personnes() {
        return nb_personnes;
    }

    public void setNb_personnes(int nb_personnes) {
        this.nb_personnes = nb_personnes;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public int getId_restau() {
        return id_restau;
    }

    public void setId_restau(int id_restau) {
        this.id_restau = id_restau;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }
}
