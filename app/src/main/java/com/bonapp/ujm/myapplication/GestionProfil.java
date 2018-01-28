package com.bonapp.ujm.myapplication;

import android.os.Bundle;

/**
 * Created by maham on 23/01/2018.
 */

public class GestionProfil extends MenuManagerActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    protected  int getLayoutId(){
        return R.layout.gestion_profil_manager;
    }
}
