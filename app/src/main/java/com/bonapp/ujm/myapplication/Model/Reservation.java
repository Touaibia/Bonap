package com.bonapp.ujm.myapplication.Model;

/**
 * Created by Nianfo on 08/02/2018.
 */

public class Reservation {

    Restaurant restaurant;
    String nb_personnes;
    String date;
    String jour;


    public Reservation() {
    }

    public Reservation(Restaurant rest, String date){
        this.restaurant = rest;
        this.date = date;
    }
}
