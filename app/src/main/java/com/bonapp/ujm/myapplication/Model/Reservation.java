package com.bonapp.ujm.myapplication.Model;

import java.util.Date;

/**
 * Created by Nianfo on 08/02/2018.
 */

public class Reservation {

    int id;
    int nb_personnes;
    int service;
    Date date;
    String heure;
    long id_restau;
    long id_client;
    Restaurant restaurant;
    Client client;

    public Reservation() {
    }

    public Reservation(int nb_personnes, int service, Date date,String heure, long id_restau, long id_client) {
        this.nb_personnes = nb_personnes;
        this.service = service;
        this.date = date;
        this.heure = heure;
        this.id_restau = id_restau;
        this.id_client = id_client;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    //Recuperer les Reserv depuis le compte Retaurant
    public Reservation(int id, int nb_personnes, int service, String heure, Client cl) {
        this.id = id;
        this.nb_personnes = nb_personnes;
        this.service = service;
        this.heure = heure;
        this.client = cl;
    }

    //Recuperer les Reserv depuis le compte Cleint
    public Reservation(int id, int nb_personnes, int service, Date date,String heure, long id_restau) {
        this.id = id;
        this.nb_personnes = nb_personnes;
        this.service = service;
        this.date = date;
        this.heure = heure;
        this.id_restau = id_restau;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public long getId_restau() {
        return id_restau;
    }

    public void setId_restau(long id_restau) {
        this.id_restau = id_restau;
    }

    public long getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }
}
