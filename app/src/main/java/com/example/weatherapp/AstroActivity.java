package com.example.weatherapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AstroActivity extends AppCompatActivity {

    private TextView sunriseTextView;
    private TextView sunsetTextView;
    private TextView moonriseTextView;
    private TextView moonsetTextView;
    private TextView moonPhaseTextView;
    private TextView moonIlluminationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astro);
        CityGlobal cityGlobal = CityGlobal.getInstance();
        String cityName = cityGlobal.getCityName();
        Log.e(TAG, "CityName: " + cityName);

        // Initialize TextViews
        sunriseTextView = findViewById(R.id.sunRiseTextView);
        sunsetTextView = findViewById(R.id.sunSetTextView);
        moonriseTextView = findViewById(R.id.moonRiseTextView);
        moonsetTextView = findViewById(R.id.moonSetTextView);
        moonPhaseTextView = findViewById(R.id.moon_phaseTextView);
        Button currentButton = findViewById(R.id.idCurrentButton);
        Button forecastButton = findViewById(R.id.idForecastButton);
        Button astroButton = findViewById(R.id.idAstroButton);
        Button hourlyButton = findViewById(R.id.idHourlyButton);

        GlobalButtonActivty globalButtonActivty = new GlobalButtonActivty();


        currentButton.setOnClickListener(globalButtonActivty);
        forecastButton.setOnClickListener(globalButtonActivty);
        astroButton.setOnClickListener(globalButtonActivty);
        hourlyButton.setOnClickListener(globalButtonActivty);


        // Fetch astro data
        WeatherAPICall.getInstance(this).fetchAstroData(cityName,new WeatherAPICall.AstroDataListener() {
            @Override
            public void onAstroDataReceived(AstroData astroData) {
                displayAstroData(astroData);
            }

            @Override
            public void onAstroDataError(String errorMessage) {
                Toast.makeText(AstroActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayAstroData(AstroData astroData) {
        sunriseTextView.setText(astroData.getSunrise());
        sunsetTextView.setText(astroData.getSunset());
        moonriseTextView.setText(astroData.getMoonrise());
        moonsetTextView.setText(astroData.getMoonset());
        moonPhaseTextView.setText(astroData.getMoonPhase());
    }
}
