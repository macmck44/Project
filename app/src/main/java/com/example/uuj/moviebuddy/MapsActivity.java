/*Developer documentation sources below used for bottom navigation view
https://developer.android.com/guide/navigation/navigation-ui#java
https://code.tutsplus.com/tutorials/how-to-code-a-bottom-navigation-bar-for-an-android-app--cms-30305

Developer documentation source below for Google Maps API
https://developers.google.com/maps/documentation/android-sdk/start*/

package com.example.uuj.moviebuddy;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity {

    private static final String finelocation = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String coarselocation = Manifest.permission.ACCESS_COARSE_LOCATION;
    private Boolean LocationPermissionGranted = false;
    private static final int locationcode = 1;
    private GoogleMap gmap;
    private FusedLocationProviderClient loc;
    private EditText cinemasearch;
    private Button findcinemas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        cinemasearch = (EditText) findViewById(R.id.editText_cinemasearch);
        findcinemas = (Button) findViewById(R.id.button_findcinemas);
        BottomNavigationView bottomNavView = (BottomNavigationView) findViewById(R.id.bottomNavView);
        Menu menu = bottomNavView.getMenu();
        MenuItem menuitem = menu.getItem(4);
        menuitem.setChecked(true);



        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.ic_home:
                        Intent intent = new Intent(MapsActivity.this, com.example.uuj.moviebuddy.HomeActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.ic_account:
                        Intent intent1 = new Intent(MapsActivity.this, com.example.uuj.moviebuddy.AccountActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_news:
                        Intent intent2 = new Intent(MapsActivity.this, com.example.uuj.moviebuddy.NewsActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_rec:
                        Intent intent3 = new Intent(MapsActivity.this, com.example.uuj.moviebuddy.RecActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.ic_maps:
                        break;

                }

                return false;
            }
        });

        getLocationPermission();

    }

    private void editTextSearch() {
        cinemasearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == KeyEvent.KEYCODE_ENTER || event.getAction() == KeyEvent.ACTION_DOWN)    {
                    findLocation();
                }

                return false;
            }
        });

        /*Google Maps, Location and Places APIs implemented into the app gradle
        * This method takes the text entered in the search bar, assigns it to a string and then calls the findLocation() method.
        * actionIds just allows you to search using search button or enter on your keyboard.*/
    }



    private void findLocation() {
        String searchresult = cinemasearch.getText().toString();
        Geocoder gc = new Geocoder(MapsActivity.this);

        List<Address> results = new ArrayList<>();
        try {
            results = gc.getFromLocationName(searchresult,1);

        }catch(IOException exc) {
            exc.getMessage();
        }

        if(results.size() > 0)    {
            Address result = results.get(0);

            moveCamera(new LatLng(result.getLatitude(), result.getLongitude()), 15f, result.getAddressLine(0));
        }

        /*Google Maps, Location and Places APIs implemented into the app gradle
        * This method takes the text entered into the search bar and assigns it to a string, uses a Geocoder to find the location from searchresult,
        * get the Latitude and Longitude of the location and move the camera to that latitude and longitude by calling the moveCamera() method.
        * Used array as there will be more results in future to display multiple cinemas.*/
    }

    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(MapsActivity.this.getApplicationContext(),
                finelocation) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(MapsActivity.this.getApplicationContext(),
                    coarselocation) == PackageManager.PERMISSION_GRANTED) {
                LocationPermissionGranted = true;
                startMap();
            } else {
                ActivityCompat.requestPermissions(MapsActivity.this, permissions, locationcode);
            }

        } else {
            ActivityCompat.requestPermissions(MapsActivity.this, permissions, locationcode);
        }
    }

    /*Google Maps, Location and Places APIs implemented into the app gradle
     *This method is another default of API that that checks the permissions to access the device's FINE and COARSE locations,
     *if location permissions are granted then call startMap() method else send the request to the user again.*/


    private void startMap() {
        SupportMapFragment mfragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mfragment.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                gmap = googleMap;
                getLocation();

                if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                gmap.setMyLocationEnabled(true);
                gmap.getUiSettings().setMyLocationButtonEnabled(true);

                editTextSearch();
            }
        });

        /*Google Maps, Location and Places APIs implemented into the app gradle
        * This method is default of API that registers the map on the screen and gets the device's FINE and COARSE locations by calling it from Manifest*/
    }

    private void getLocation()  {
        loc = LocationServices.getFusedLocationProviderClient(MapsActivity.this);
        try {
            if(LocationPermissionGranted)   {
                Task location = loc.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        Location currentLoc = (Location) task.getResult();
                        moveCamera(new LatLng(currentLoc.getLatitude(), currentLoc.getLongitude()), 15f, "Current Location");
                    }
                });
            }

        } catch (SecurityException exc){
            exc.getMessage();
        }

        /*Google Maps, Location and Places APIs implemented into the app gradle
        * This method takes the current location from calling findLocation() method and moving the camera by
        * calling the moveCamera() method.*/

    }

    private void moveCamera(LatLng latLng, float zoom, String title)   {
        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        if(!title.equals("Current Location")) {
            MarkerOptions markeroption = new MarkerOptions().position(latLng).title(title);
            gmap.addMarker(markeroption);
        }

        /*Google Maps, Location and Places APIs implemented into the app gradle
        * Google Services method that calls the CameraUpdateFactory method to move the Google maps camera to a new Latitude and
        * Longitude. MarkerOptions is a Google Services method that allows you to add a marker to the location along with the title and address.*/

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        LocationPermissionGranted = false;

        switch(requestCode) {
            case locationcode:{
                if(grantResults.length > 0) {
                    for(int i = 0; i < grantResults.length; i++)    {
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED)    {
                            LocationPermissionGranted = false;
                            return;
                        }
                        LocationPermissionGranted = true;
                        startMap();
                    }
                }
            }
        }

        /*Google Maps, Location and Places APIs implemented into the app gradle
        * This method is default of API with an if statement that checks that permission is granted and if so calls the startMap() method.*/
    }

}
