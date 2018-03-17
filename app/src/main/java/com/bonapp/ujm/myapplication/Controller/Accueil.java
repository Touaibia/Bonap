package com.bonapp.ujm.myapplication.Controller;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
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
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bonapp.ujm.myapplication.Model.Adresse;
import com.bonapp.ujm.myapplication.Model.BaseDonnees;
import com.bonapp.ujm.myapplication.Model.Client;
import com.bonapp.ujm.myapplication.Model.RepoAdresse;
import com.bonapp.ujm.myapplication.Model.RepoClientRestoFavori;
import com.bonapp.ujm.myapplication.Model.RepoRestaurant;
import com.bonapp.ujm.myapplication.Model.RepoTypeCuisine;
import com.bonapp.ujm.myapplication.Model.RepoTypeCuisineRestaurant;
import com.bonapp.ujm.myapplication.Model.Restaurant;
import com.bonapp.ujm.myapplication.Model.TypeCuisine;
import com.bonapp.ujm.myapplication.R;
import com.bonapp.ujm.myapplication.Model.SuggestionRestoAdapter;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;

public class Accueil extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {


    LocationManager locationManager;
    GoogleMap gMap;
    LatLng latLng;
    double lng;
    double ltd;
    List listLTLG = new ArrayList<>();
    String[] adress = {"2 rue camille colard 42000 saint-etienne", "88 rue antoine durafour"};
    BaseDonnees db;

    static float distanceDemance = 0;
    static float zoom = 10;
    String budget;
    String type;
    String typeAmbiance;
    List<Restaurant> list = new ArrayList<Restaurant>();
    Context c;
    long idclient;
    MapFragment mapFragment;
    ViewGroup.LayoutParams params;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        c = this;

        RepoRestaurant repo = new RepoRestaurant(this);
        repo.open();
        Cursor cursor = repo.DB.rawQuery("select password from contacts",null);
        while(cursor.moveToNext()){
       //     Toast.makeText(this,"id client "+cursor.getString(0),Toast.LENGTH_LONG).show();
        }

        //list.add(new Restaurant());
        //recuperer id du client
        //repo.DB.execSQL("DROP TABLE IF EXISTS reservation");

        Intent intent = getIntent();
        long id = intent.getLongExtra("idclient",-1);
        if(id!=-1) idclient = id;

        TextView rech = (TextView) findViewById(R.id.filtreRech);
        rech.setOnClickListener(this);

         mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mmap);
        mapFragment.getMapAsync(this);
        params = mapFragment.getView().getLayoutParams();
        TextView recommandation = (TextView) findViewById(R.id.recommandation);
        if(list.size()!=0) {
            params.height = 800;
            mapFragment.getView().setLayoutParams(params);
            recommandation.setVisibility(View.VISIBLE);
            RecyclerView listv = (RecyclerView) findViewById(R.id.list);
            listv.setLayoutManager(new LinearLayoutManager(this));
            listv.setAdapter(new SuggestionRestoAdapter(list, idclient));
        }else{
            params.height = 1500;
            mapFragment.getView().setLayoutParams(params);
            recommandation.setVisibility(View.INVISIBLE);
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
                final AlertDialog.Builder builder = new AlertDialog.Builder(Accueil.this);
                View view2 = getLayoutInflater().inflate(R.layout.layout_filtre, null);
                //Recupératio de tous les types de cuisine en BD
                RepoTypeCuisine repoTypeCuisine = new RepoTypeCuisine(this);
                repoTypeCuisine.open();
                ArrayList<TypeCuisine> lesTypes = repoTypeCuisine.selectionner();
                repoTypeCuisine.close();
                final List<String> typeIdNom = new ArrayList<>();
                //Création d'une liste nomType_id pour le spinner
                List<String> tab1 = new ArrayList<>();
                for (int i=0; i < lesTypes.size(); i++){
                    tab1.add(""+lesTypes.get(i).getNom());
                    typeIdNom.add(""+lesTypes.get(i).getNom()+"_"+lesTypes.get(i).getId());

                }

                tab1.add("Autre");

                final Spinner spinner = (Spinner) view2.findViewById(R.id.typegoutclient);
                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<String> adapter1;
                adapter1 = new  ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tab1);
                // Specify the layout to use when the list of choices appears
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                spinner.setAdapter(adapter1);
                final String[] type = new String[1];
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {

                        type[0] = parent.getItemAtPosition(pos).toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                final Spinner distance = (Spinner) view2.findViewById(R.id.distance);
                builder.setTitle(" Filtre votre rechercher");

                List<String> tab = new ArrayList<>();
                int j;
                for (j = 1; j < 20; j++){
                    tab.add(j+" km");
                }
                tab.add(60+" km");
                tab.add("plus");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tab);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                distance.setAdapter(adapter);
                final String[] dist = new String[1];
                distance.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                       dist[0] = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                builder.setPositiveButton("VALIDE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        View view1 = View.inflate(getApplicationContext(), R.layout.layout_filtre, null);
                        EditText adresse = (EditText) view1.findViewById(R.id.Position);
                        String adress = adresse.getText().toString();
                        String[] d = dist[0].split(" ");
                        Spinner budget = (Spinner) view1.findViewById(R.id.budget);
                        String b = budget.getSelectedItem().toString();

                        if(!d[0].equals("plus")) {
                            distanceDemance =(float) parseInt(d[0]);
                            if(distanceDemance == (float)60) zoom = 7;
                            localisationDesRestau();

                        }

                        RepoTypeCuisineRestaurant repo = new RepoTypeCuisineRestaurant(getApplicationContext());
                        repo.open();
                        int j;
                        for(j=0;j<typeIdNom.size();j++){
                            String[] typeidnom = typeIdNom.get(j).split("_");
                            if(type[0].equals(typeidnom[0])){

                                TextView recommandation = (TextView) findViewById(R.id.recommandation);
                                recommandation.setVisibility(View.VISIBLE);

                                params.height = 800;
                                mapFragment.getView().setLayoutParams(params);

                                list = repo.selectionnerRestau(parseInt(typeidnom[1]));
                                Toast.makeText(getApplicationContext(),list.get(0).getNom(),Toast.LENGTH_LONG).show();
                                RecyclerView listv = (RecyclerView) findViewById(R.id.list);

                                listv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                listv.setAdapter(new SuggestionRestoAdapter(list,idclient));
                                TextView rech = (TextView) findViewById(R.id.filtreRech);
                                rech.setOnClickListener((View.OnClickListener) c);
                            }
                        }




                    }
                });

                builder.setView(view2);
                builder.create().show();


                break;




        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.accueil:
               Intent intent0 = new Intent(this, Accueil.class);
                intent0.putExtra("idclient",idclient);
                startActivity(intent0);
                return true;
            case R.id.profile:
                Intent intent11 = new Intent(this, profilclient.class);
                intent11.putExtra("idclient",idclient);
                startActivity(intent11);
                return true;
            case R.id.Reservation:
                Intent intentm = new Intent(this, MesReservations.class);
                intentm.putExtra("idclient",idclient);
                startActivity(intentm);
                return true;
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
      // localisationClient();


    }

    public void localisationDesRestau(){
        Geocoder geocoder = new Geocoder(getApplicationContext());
        RepoAdresse repoAd = new RepoAdresse(this);
        repoAd.open();
        List<Adresse> list = new ArrayList<>();
        list = repoAd.plusProcheRestoAdresse();
        localisationClient();
        try {
            int i;
            for (i = 0; i < list.size(); i++) {
                //localistion de l'adresse de restaurant
                List<Address> addresses = null;
                final Adresse ad = list.get(i);
                String adr = ad.getNumero() + " " + ad.getType_voie() + " " + ad.getIntitule() + " " + ad.getCode_postal();
                addresses = geocoder.getFromLocationName(adr, 1);
                double adresslat = addresses.get(0).getLatitude();
                double adresslong = addresses.get(0).getLongitude();

                // recuperation de la position du client
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                double currentlat = location.getLatitude();
                double currentlon = location.getLongitude();

                //calcule de la distance
                float[] results = new float[10];
                Location.distanceBetween(adresslat,adresslong,currentlat,currentlon,results);

                float d = results[0]/1000;
                if(d<=distanceDemance) {
                    Marker marker = gMap.addMarker(new MarkerOptions().position(new LatLng(adresslat, adresslong)).title("" + results[0] / 1000));
                    marker.setTag(0);
                    gMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(c);
                            View resto = getLayoutInflater().inflate(R.layout.visiteresto, null);
                            long idres = ad.getId_retau();
                            RepoRestaurant repo = new RepoRestaurant(c);
                            repo.open();
                            final Restaurant restaurant = repo.selectionnerAccueil(idres);
                            TextView restauNom = resto.findViewById(R.id.restauNomVisite);

                            builder.setPositiveButton("Page du restaurant", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(c,PageRestaurant.class);
                                    intent.putExtra("nomRestau",restaurant.getNom());
                                    intent.putExtra("idrestau",restaurant.getId());
                                    intent.putExtra("idclient",idclient);
                                    startActivity(intent);
                                }
                            });
                            restauNom.setText(restaurant.getNom());
                            repo.close();
                            builder.setView(resto);
                            builder.create().show();
                            Toast.makeText(getApplicationContext(), "okk", Toast.LENGTH_LONG).show();
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
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    latLng = new LatLng(ltd, lng);

                    try {

                        List<Address> addresses1 =  geocoder.getFromLocation(ltd,lng,1);

                        String str1 = addresses1.get(0).getLocality() + ",";
                        str1 += addresses1.get(0).getCountryName() +",";
                        str1 += addresses1.get(0).getFeatureName();
                        gMap.addMarker(new MarkerOptions()
                                .position(latLng).title(""+ltd)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                        );
                        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

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
                        gMap.addMarker(new MarkerOptions().position(latLng).title(""+ltd).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
                        ));
                        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

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

    public double distance(double ltd1,double lgt1, double ltd2, double lgt2){
        ltd1 = ltd1*Math.PI/180;
        ltd2 = ltd2*Math.PI/180;
        lgt1 = lgt1*Math.PI/180;
        lgt2 = lgt2*Math.PI/180;
        double d =  Math.cos(Math.sin(ltd1)*Math.sin(ltd2)+Math.cos(ltd1)*Math.cos(ltd2)*Math.cos(lgt2-lgt1))*6371;

        return d;
    }

}
