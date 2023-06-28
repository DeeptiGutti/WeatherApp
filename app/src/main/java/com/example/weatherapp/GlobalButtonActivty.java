package com.example.weatherapp;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GlobalButtonActivty extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buttons_layout); // Set the appropriate layout file

        SharedPreferences sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String cityName = sharedPref.getString("CityName", "");
        Log.e(TAG, "CityName: " + cityName);
        if (cityName != null && !cityName.isEmpty()) {
            // Set the click listeners for the buttons
            findViewById(R.id.idCurrentButton).setOnClickListener(this);
            findViewById(R.id.idForecastButton).setOnClickListener(this);
            findViewById(R.id.idAstroButton).setOnClickListener(this);
            findViewById(R.id.idHourlyButton).setOnClickListener(this);
        } else {
            // Handle the case when cityName is null or empty
            Toast.makeText(this, "City name is not available", Toast.LENGTH_SHORT).show();
            // Optionally, you can disable or hide the buttons if cityName is null
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.idCurrentButton:
                intent = new Intent(v.getContext(), WeatherActivity.class);
                v.getContext().startActivity(intent);
                Toast.makeText(v.getContext(), "Current button clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.idForecastButton:
                intent = new Intent(v.getContext(), ForecastActivity.class);
                v.getContext().startActivity(intent);
                Toast.makeText(v.getContext(), "Forecast button clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.idAstroButton:
                intent = new Intent(v.getContext(), AstroActivity.class);
                v.getContext().startActivity(intent);
                Toast.makeText(v.getContext(), "Astro button clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.idHourlyButton:
                intent = new Intent(v.getContext(), HourlyActivity.class);
                v.getContext().startActivity(intent);
                Toast.makeText(v.getContext(), "Hourly button clicked", Toast.LENGTH_SHORT).show();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}
