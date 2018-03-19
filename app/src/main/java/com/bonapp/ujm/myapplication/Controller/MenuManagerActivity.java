package com.bonapp.ujm.myapplication.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.bonapp.ujm.myapplication.Model.SharedPrefManager;
import com.bonapp.ujm.myapplication.R;

/**
 * Created by maham on 23/01/2018.
 */

public abstract class MenuManagerActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
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
                break;
            case R.id.event:
            /* DO ADD */
                startActivity(new Intent(MenuManagerActivity.this, GestionEvent.class));
                break;
            case R.id.profil:
            /* DO DELETE */
                startActivity(new Intent(MenuManagerActivity.this, GestionProfil.class));
                break;
            case R.id.stat:
            /* DO DELETE */
                break;
            case R.id.opt:
            /* DO DELETE */
                SharedPrefManager.getInstance(this).LogOut();
                finish();
                startActivity(new Intent(this,loginActivity.class));
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }
}
