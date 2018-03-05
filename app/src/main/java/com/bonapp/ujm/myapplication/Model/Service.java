package com.bonapp.ujm.myapplication.Model;
import java.util.Date;
/**
 * Created by Davy on 05/03/2018.
 */

public class Service {
    int service;
    Date date;

    public Service(int s, Date d){
        this.service = s;
        this.date = d;
    }

    public int getService() {
        return service;
    }

    public Date getDate() {
        return date;
    }
}
