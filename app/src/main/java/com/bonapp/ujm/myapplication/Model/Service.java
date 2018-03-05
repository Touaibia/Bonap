package com.bonapp.ujm.myapplication.Model;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.ZoneId;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getDateYear() {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getYear();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getDateMonth() {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getMonthValue();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getDateDay() {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getDayOfMonth();
    }

}
