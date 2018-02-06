package com.bonapp.ujm.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Accueil extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {


    LocationManager locationManager;
    GoogleMap gMap;
    LatLng latLng;
    Location location;
    double lng;
    double ltd;

   // FusedLocationProviderClient mFusedLocationClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        //Toolbar toolb = findViewById(R.id.toolbar);
       // toolb.setTitle("okkkkkk");

        Toast.makeText(this,"ok1",Toast.LENGTH_LONG);
        Restaurant R1 = new Restaurant("R1", R.drawable.common_full_open_on_phone);
        Restaurant R2 = new Restaurant("R2", R.drawable.icons8menuutilisateurhomme50);
        Restaurant R3 = new Restaurant("R3", R.drawable.common_google_signin_btn_icon_dark_normal);
        Restaurant R4 = new Restaurant("R4", R.drawable.icons8fork50);
        Restaurant R5 = new Restaurant("R4", R.drawable.icons8fork50);
        Restaurant R6 = new Restaurant("R4", R.drawable.icons8fork50);
        List<Restaurant> list = new ArrayList<Restaurant>();
        list.add(R1);
        list.add(R2);
        list.add(R3);
        list.add(R4);
        list.add(R5);
        list.add(R6);


        RecyclerView listv = (RecyclerView) findViewById(R.id.list);

        listv.setLayoutManager(new LinearLayoutManager(this));
        listv.setAdapter(new SuggestionRestoAdapter(list));
        TextView rech = (TextView) findViewById(R.id.filtreRech);
        rech.setOnClickListener(this);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mmap);
        mapFragment.getMapAsync(this);



        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        gMap.setMyLocationEnabled(true);

        if (locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    lng = location.getLongitude();
                    ltd = location.getLatitude();

                    latLng = new LatLng(ltd, lng);
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(ltd, lng, 1);
                        String str = addresses.get(0).getLocality() + ",";
                        str += addresses.get(0).getCountryName();
                        gMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        // gMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
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


            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double lng = location.getLongitude();
                    double ltd = location.getLatitude();
                    latLng = new LatLng(ltd, lng);
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(ltd, lng, 1);
                        String str = addresses.get(0).getLocality() + ",";
                        str += addresses.get(0).getCountryName();
                        gMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        // gMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
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

                return  true;

        }

        return super.onOptionsItemSelected(item);
   }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        LatLng sd = new LatLng(32,152);
        gMap.addMarker(new MarkerOptions().position(sd).title("Marker"));
       // gMap.moveCamera(CameraUpdateFactory.newLatLng(sd));
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sd, 10.2f));

        /*lng = location.getLongitude();
        ltd = location.getLatitude();
        latLng = new LatLng(ltd,lng);
        Geocoder geocoder = new Geocoder(getApplicationContext());
        List <Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(ltd,lng,2);
            String str = addresses.get(0).getLocality()+",";
            str +=addresses.get(0).getCountryName();
            gMap.addMarker(new MarkerOptions().position(new LatLng(ltd,lng)).title(str));
            gMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,10.2f));
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }


}
