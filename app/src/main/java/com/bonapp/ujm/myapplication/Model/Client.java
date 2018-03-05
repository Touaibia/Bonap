package com.bonapp.ujm.myapplication.Model;

import android.location.Address;

/**
 * Created by Smaïl on 23/02/2018.
 */

public class Client {

    String username;
    String email;
    String password;
    Adresse adress;
    String city;
    String phone;
    String type;

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

    public void setAdress(Adresse adress) {
        this.adress = adress;
    }

    public Adresse getAdress() {
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
