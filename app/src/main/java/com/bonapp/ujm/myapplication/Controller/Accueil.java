package com.bonapp.ujm.myapplication.Controller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bonapp.ujm.myapplication.Model.Adresse;
import com.bonapp.ujm.myapplication.Model.BaseDonnees;
import com.bonapp.ujm.myapplication.Model.Restaurant;
import com.bonapp.ujm.myapplication.R;
import com.bonapp.ujm.myapplication.Model.SuggestionRestoAdapter;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Accueil extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {


    LocationManager locationManager;
    GoogleMap gMap;
    LatLng latLng;
    double lng;
    double ltd;
    String [] adress = {"2 rue camille colard 42000 saint-etienne","88 rue antoine durafour"};
    BaseDonnees db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        List<Restaurant> list = new ArrayList<Restaurant>();
        //db = new BaseDonnees(this);
       // db.open();
        // insertion des données
       // db.insertResto("Resto 1","Since 1889,Italien",""+R.drawable.planchecharcuterie);
        //db.insertResto("Resto 2","Since 1889,Italien",""+R.drawable.planchecharcuterie);
       /*if( db.insertResto("resto 3","Since 1879,Italien",""+R.drawable.icons8fork50)){
           Toast.makeText(this,"Insertion ok",Toast.LENGTH_LONG).show();
        }
        else {
           Toast.makeText(this,"echec d'insertion",Toast.LENGTH_LONG).show();
       }*/

        // recuperation des données dans la base "pas operationnel pour le moment
       /* Cursor cursor = db.getAllResto();
        while (cursor.moveToNext()){
            list.add(new Restaurant(cursor.getString(1),cursor.getInt(2)));
        }*/

        Toast.makeText(this,"ok1",Toast.LENGTH_LONG);
        Restaurant R1 = new Restaurant("Rest 1","email","1234",
                new Adresse("2","rue","Camille Colard","42000"),"0638927926");
        R1.setImage(R.drawable.icons8couverts50);


        list.add(R1);

        RecyclerView listv = (RecyclerView) findViewById(R.id.list);

        listv.setLayoutManager(new LinearLayoutManager(this));
        listv.setAdapter(new SuggestionRestoAdapter(list));
        TextView rech = (TextView) findViewById(R.id.filtreRech);
        rech.setOnClickListener(this);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mmap);
        mapFragment.getMapAsync( this);



        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                gMap.setMyLocationEnabled(true);
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    lng = location.getLongitude();
                    ltd = location.getLatitude();

                    latLng = new LatLng(ltd, lng);
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {

                        List<Address> addresses1 =  geocoder.getFromLocation(ltd,lng,1);

                        String str1 = addresses1.get(0).getLocality() + ",";
                        str1 += addresses1.get(0).getCountryName() +",";
                        str1 += addresses1.get(0).getFeatureName();
                        gMap.addMarker(new MarkerOptions()
                                .position(latLng).title(str1)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                        );
                        gMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10.2f));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        } else if (locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)) {


            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double lng = location.getLongitude();
                    double ltd = location.getLatitude();
                    latLng = new LatLng(ltd, lng);
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {

                        //localisation de l'utilisateur
                        List<Address> addresses1 =  geocoder.getFromLocation(ltd,lng,1);

                        String str1 = addresses1.get(0).getLocality() + ",";
                        str1 += addresses1.get(0).getCountryName() +",";
                        str1 += addresses1.get(0).getFeatureName();
                        gMap.addMarker(new MarkerOptions().position(latLng).title(str1).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
                        ));
                        gMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10.2f));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {
                    Toast.makeText( getApplicationContext(),"Gps enabled",Toast.LENGTH_SHORT ).show();
                }

                @Override
                public void onProviderDisabled(String s) {
                    Toast.makeText( getApplicationContext(),"Gps Disabled", Toast.LENGTH_LONG ).show();
                }
            });

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.filtreRech:
                AlertDialog.Builder builder = new AlertDialog.Builder(Accueil.this);
                View view2 = getLayoutInflater().inflate(R.layout.layout_filtre, null);
                TextView nview = (TextView) view2.findViewById(R.id.Position);
                Spinner spd = (Spinner) view.findViewById(R.id.distance);
                Button buttonfiltre = (Button) view2.findViewById(R.id.buttonFiltre);
                builder.setTitle(" Filtre votre rechercher");
                builder.setView(view2);

                builder.create().show();
                break;
        }
    }
    @Override
   public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.accueil:
                startActivity(new Intent(this,Accueil.class));
                return  true;
            case R.id.profile:
                startActivity(new Intent(this,profilclient.class));
                return  true;
            case R.id.Reservation:
                startActivity(new Intent(this,MesReservations.class));
                return  true;


        }

        return super.onOptionsItemSelected(item);
   }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        Geocoder geocoder = new Geocoder(getApplicationContext());

        try {
            int i;
            for(i=0;i<adress.length;i++) {
                List<Address> addresses = null;

                    addresses = geocoder.getFromLocationName(adress[i], 1);

                double ltde = addresses.get(0).getLatitude();
                final double lont = addresses.get(0).getLongitude();
                Marker marker = gMap.addMarker(new MarkerOptions().position(new LatLng(ltde, lont)).title(adress[i]));
                marker.setTag(0);
                gMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(getApplicationContext(), "okk", Toast.LENGTH_LONG).show();
                        return false;
                    }
                });
        }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
