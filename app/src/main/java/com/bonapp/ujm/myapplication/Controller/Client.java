package com.bonapp.ujm.myapplication.Controller;

import android.os.Parcel;
import android.os.Parcelable;

import com.bonapp.ujm.myapplication.Model.Restaurant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smaïl on 23/02/2018.
 */

public class Client implements Serializable {

    String username,email,password,adress,city,phone,type;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    List<Restaurant> restoFavoris = new ArrayList<>();

    public void setRestoFavoris(Restaurant r){
        this.restoFavoris.add(r);
    }
    public List<Restaurant> getRestoFavoris(){
        return restoFavoris;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getAdress() {
        return adress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
