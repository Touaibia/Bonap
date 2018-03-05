package com.bonapp.ujm.myapplication.Model;

/**
 * Created by Nianfo on 08/02/2018.
 */

public class Reservation {

    Restaurant restaurant;
    int nb_personnes;
    Service dataService;


    public Reservation() {
    }

    public Reservation(Restaurant rest, int nb_personnes, Service ds){
        this.restaurant = rest;
        this.nb_personnes = nb_personnes;
        this.dataService = ds;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getNb_personnes() {
        return nb_personnes;
    }

    public void setNb_personnes(int nb_personnes) {
        this.nb_personnes = nb_personnes;
    }

    public Service getDataService() {
        return dataService;
    }

    public void setDataService(Service dataService) {
        this.dataService = dataService;
    }
}
