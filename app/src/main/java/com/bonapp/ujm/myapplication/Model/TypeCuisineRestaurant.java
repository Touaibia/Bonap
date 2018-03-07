package com.bonapp.ujm.myapplication.Model;

/**
 * Created by maham on 07/03/2018.
 */

public class TypeCuisineRestaurant {
    public int id;
    public int id_type;
    public int id_restau;

    public TypeCuisineRestaurant(int id, int id_type, int id_restau) {
        this.id = id;
        this.id_type = id_type;
        this.id_restau = id_restau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public int getId_restau() {
        return id_restau;
    }

    public void setId_restau(int id_restau) {
        this.id_restau = id_restau;
    }
}
