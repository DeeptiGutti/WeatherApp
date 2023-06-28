package com.example.weatherapp;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetCityNameActivity extends AppCompatActivity implements LocationListener {

    private static final int PERMISSION_REQUEST_CODE = 123;

    private LocationManager locationManager;
    private boolean isLocationRetrieved = false;
    private String cityName = "Santa Clara";  // Default city name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"In the get cityNameActivity");

        // Request location permissions and start retrieving location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_CODE);
            Log.e(TAG, "In the get cityNameActivity,Granting permission");
        } else {
            // Permissions are already granted, start retrieving location
            getLocation();
            Log.e(TAG,"In the get cityNameActivity,Permission granted");

        }
    }

    private void getLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Check if location services are enabled
        if (locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // Permissions not granted, handle accordingly
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        } else {
            Toast.makeText(this, "Please enable GPS", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (!isLocationRetrieved) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            // Pass the latitude and longitude to getCityName() method
            getCityName(latitude, longitude);
            isLocationRetrieved = true;
        }
    }

    private void getCityName(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                cityName = address.getLocality();

                // Check if locality is null, try using other address components
                if (cityName == null) {
                    cityName = address.getAdminArea();
                }
                if (cityName == null) {
                    cityName = address.getSubAdminArea();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            cityName = "Santa Clara,CA";
        }

        // Store the city name in the GlobalData
        CityGlobal cityGlobal = CityGlobal.getInstance();
        cityGlobal.setCityName(cityName);
        Log.e(TAG, "getCityName:" + cityName);

// Start the WeatherActivity
        Intent intent = new Intent(GetCityNameActivity.this, WeatherActivity.class);
        startActivity(intent);
// Finish the current activity
        finish();


    }




}
