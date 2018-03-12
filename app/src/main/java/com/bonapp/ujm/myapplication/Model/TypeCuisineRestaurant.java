package com.bonapp.ujm.myapplication.Model;

/**
 * Created by maham on 07/03/2018.
 */

public class TypeCuisineRestaurant {
    public long id;
    public long id_type;
    public long id_restau;

    public TypeCuisineRestaurant(long id, long id_type, long id_restau) {
        this.id = id;
        this.id_type = id_type;
        this.id_restau = id_restau;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public long getId_restau() {
        return id_restau;
    }

    public void setId_restau(int id_restau) {
        this.id_restau = id_restau;
    }
}
