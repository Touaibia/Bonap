package com.bonapp.ujm.myapplication.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.bonapp.ujm.myapplication.R;

/**
 * Created by maham on 23/01/2018.
 */

public abstract class MenuManagerActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setTitle("BonApp");
    }

    protected abstract int getLayoutId();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.manager_menu, menu);
        return true;
    }

    public void gestClick(MenuItem item){
        switch (item.getItemId()) {
            case R.id.pub:
                startActivity(new Intent(MenuManagerActivity.this, GestionPublication.class));
            case R.id.event:
            /* DO ADD */

            case R.id.profil:
            /* DO DELETE */
                startActivity(new Intent(MenuManagerActivity.this, GestionProfil.class));
            case R.id.stat:
            /* DO DELETE */

            case R.id.opt:
            /* DO DELETE */

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }
}
