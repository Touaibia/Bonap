package com.bonapp.ujm.myapplication.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bonapp.ujm.myapplication.R;

public class GestionEvent extends MenuManagerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gestion_event;
    }
}
