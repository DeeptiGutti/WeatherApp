package com.example.weatherapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.weatherapp.ForecastActivity;
import com.example.weatherapp.WeatherActivity;

public class ForecastActivity extends AppCompatActivity {

    private TextView dateTextView,minTemperature,maxTemperature,avgTemperature,chancesRain,
    chacesSnow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        CityGlobal cityGlobal = CityGlobal.getInstance();
        String cityName = cityGlobal.getCityName();
        Log.e(TAG, "CityName: " + cityName);




        minTemperature = findViewById(R.id.minTemperatureTextView);
        maxTemperature = findViewById(R.id.maxTemperatureTextView);
        avgTemperature = findViewById(R.id.avgTemperatureTextView);
        chancesRain = findViewById(R.id.rainChancesTextView);
        chacesSnow = findViewById(R.id.snowChancesTextView);

        Button currentButton = findViewById(R.id.idCurrentButton);
        Button forecastButton = findViewById(R.id.idForecastButton);
        Button astroButton = findViewById(R.id.idAstroButton);
        Button hourlyButton = findViewById(R.id.idHourlyButton);

        GlobalButtonActivty globalButtonActivty = new GlobalButtonActivty();
        currentButton.setOnClickListener(globalButtonActivty);
        forecastButton.setOnClickListener(globalButtonActivty);
        astroButton.setOnClickListener(globalButtonActivty);
        hourlyButton.setOnClickListener(globalButtonActivty);

        WeatherAPICall.getInstance(this).fetchForecastData(cityName, new WeatherAPICall.ForecastDataListener() {
            @Override
            public void onForecastDataReceived(ForecastData forecastData) {
                displayForecastData(forecastData);
            }

            @Override
            public void onForecastDataError(String errorMessage) {
                Toast.makeText(ForecastActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayForecastData(ForecastData forecastData) {

        avgTemperature.setText("Average Temperature "+String.valueOf(forecastData.getAvgTempC())+"°C");
        maxTemperature.setText("Maximum Temperature "+String.valueOf(forecastData.getMaxTempC())+"°C");
        minTemperature.setText("Minimum Temperature "+String.valueOf(forecastData.getMinTempC())+"°C");
        chancesRain.setText(String.valueOf("Chances of Rain " + forecastData.getChanceOfRain()));
        chacesSnow.setText("Chances of Snow "+String.valueOf(forecastData.getChanceOfSnow()));

    }
}
