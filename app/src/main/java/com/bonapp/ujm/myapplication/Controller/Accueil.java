package com.bonapp.ujm.myapplication.Controller;

import android.Manifest;
import android.content.Context;
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
import com.bonapp.ujm.myapplication.Model.Client;
import com.bonapp.ujm.myapplication.Model.RepoAdresse;
import com.bonapp.ujm.myapplication.Model.RepoClientRestoFavori;
import com.bonapp.ujm.myapplication.Model.RepoRestaurant;
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

import static java.lang.Math.abs;

public class Accueil extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {


    LocationManager locationManager;
    GoogleMap gMap;
    LatLng latLng;
    double lng;
    double ltd;
    List listLTLG = new ArrayList<>();
    String [] adress = {"2 rue camille colard 42000 saint-etienne","88 rue antoine durafour"};
    BaseDonnees db ;

    String distance;
    String budget;
    String type;
    String typeAmbiance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        List<Restaurant> list = new ArrayList<Restaurant>();

        RepoAdresse rep = new RepoAdresse(this);
        rep.open();
       // rep.DB.execSQL("DROP TABLE adresse");

              rep.insertAdresse(new Adresse(2,"rue","Camille Colard","42000"),0);

              rep.insertAdresse(new Adresse(1,"Place","Villeboeuf","42000"),0);
              rep.insertAdresse(new Adresse(2,"Rue","des Forces","69002"),1);

        //BaseDonnees db = new BaseDonnees(this);
        //db.open();1 Place Villebœuf, 42000
        RepoRestaurant repo = new RepoRestaurant(this);
       // repo.ajouteRestaurant(R1);
        //repo.ajouteRestaurant(R2);

       /* List<Restaurant> l = repo.;
        for (int i =0 ;i<l.size();i++) {
            l.get(i).setImage(R.drawable.icons8couverts50);
            list.add(l.get(i));
        }*/

       list.add(new Restaurant());

        RecyclerView listv = (RecyclerView) findViewById(R.id.list);

        listv.setLayoutManager(new LinearLayoutManager(this));
        listv.setAdapter(new SuggestionRestoAdapter(list));
        TextView rech = (TextView) findViewById(R.id.filtreRech);
        rech.setOnClickListener(this);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mmap);
        mapFragment.getMapAsync( this);






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
            case R.id.buttonFiltre:
                Spinner distance = (Spinner) findViewById(R.id.distance);
                String d = distance.getSelectedItem().toString();
                Spinner budget = (Spinner) findViewById(R.id.budget);
                String b = distance.getSelectedItem().toString();
                Spinner type = (Spinner) findViewById(R.id.type);
                String t = distance.getSelectedItem().toString();

                this.distance = d;
                //Toast.makeText(this, d, Toast.LENGTH_LONG).show();

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
            case R.id.Reglage:
                AlertDialog.Builder builder = new AlertDialog.Builder(Accueil.this);
                View view2 = getLayoutInflater().inflate(R.layout.parametre, null);
                builder.setTitle("Parametre");
                TextView pseudo = view2.findViewById(R.id.pseudo);
                TextView email = view2.findViewById(R.id.clientemail);
                Intent intent = getIntent();
               // Client client = (Client) intent.getSerializableExtra("client");
                String us = intent.getStringExtra("client");
                pseudo.setText(us);
                //pseudo.setText(us);
               // email.setText(client.getEmail());
                builder.setView(view2);

                builder.create().show();
                return true;


        }

        return super.onOptionsItemSelected(item);
   }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        final Context  c = this;
        Geocoder geocoder = new Geocoder(getApplicationContext());
        RepoAdresse repoAd = new RepoAdresse(this);
        List<Adresse> list = new ArrayList<>();
        list = repoAd.getAdresseProche();
        RepoRestaurant rp = new RepoRestaurant(this);

        Toast.makeText(this,""+list.size(),Toast.LENGTH_LONG);
        localisationClient();
        try {
            int i;
            for(i=0;i<list.size();i++) {
                Toast.makeText(this,"okkk "+list.get(i).getId(),Toast.LENGTH_LONG);
                // addresses = geocoder.getFromLocationName(adress[i], 1);
                List<Address> addresses = null;
                Adresse ad = list.get(i);
                    String adr =ad.getNumero()+" "+ad.getType_voie()+" "+ad.getIntitule()+" "+ad.getCode_postal();
                addresses = geocoder.getFromLocationName(adr, 1);


                double ltde = addresses.get(0).getLatitude();
                Toast.makeText(this,""+ltde,Toast.LENGTH_LONG);

                double lont = addresses.get(0).getLongitude();
                Toast.makeText(this,""+lont,Toast.LENGTH_LONG);
                //Toast.makeText(this,""+listLTLG.get(0)+" "+listLTLG.get(1) ,Toast.LENGTH_LONG);
                double dist = calculeDistance(ltde,lont,ltd,lng);
                int d=0;
            if(d<10) {
                Marker marker = gMap.addMarker(new MarkerOptions().position(new LatLng(ltde, lont)).title(adr));
                marker.setTag(0);
                gMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(c);
                        View resto = getLayoutInflater().inflate(R.layout.visiteresto, null);
                        builder.setView(resto);
                        builder.create().show();

                        //Toast.makeText(getApplicationContext(), "okk", Toast.LENGTH_LONG).show();
                        return false;
                    }
                });
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void localisationClient(){

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

                  //  RepoAdresse repoAdresse = new RepoAdresse(this);

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
                        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
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
                    listLTLG.add(ltd);
                    listLTLG.add(lng);
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
                        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
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

    public double calculeDistance(double ltde,double lng,double ltde2,double lng2){
        double x = Math.pow(abs(ltde)-abs(ltde2),2);
        double y = Math.pow(abs(lng)-abs(lng2),2);
        double d = Math.sqrt(x+y);

        return d;
    }

    public void plusProcheResto(int d){

    }


}
