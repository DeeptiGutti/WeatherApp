package com.example.weatherapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class HourlyActivity extends AppCompatActivity implements WeatherAPICall.HourlyWeatherDataListener {

    private RecyclerView recyclerView;
    private HourlyAdapter hourlyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly);
        CityGlobal cityGlobal = CityGlobal.getInstance();
        String cityName = cityGlobal.getCityName();
        Log.e(TAG, "CityName: " + cityName);

        Button currentButton = findViewById(R.id.idCurrentButton);
        Button forecastButton = findViewById(R.id.idForecastButton);
        Button astroButton = findViewById(R.id.idAstroButton);
        Button hourlyButton = findViewById(R.id.idHourlyButton);

        GlobalButtonActivty globalButtonActivty = new GlobalButtonActivty();

        currentButton.setOnClickListener(globalButtonActivty);
        forecastButton.setOnClickListener(globalButtonActivty);
        astroButton.setOnClickListener(globalButtonActivty);
        hourlyButton.setOnClickListener(globalButtonActivty);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        hourlyAdapter = new HourlyAdapter();
        recyclerView.setAdapter(hourlyAdapter);

        WeatherAPICall.getInstance(this).fetchHourlyData(cityName, this);
    }

    @Override
    public void onHourlyWeatherDataReceived(HourlyData hourlyData) {
        List<HourlyWeather> hourlyWeatherList = hourlyData.getHourlyWeatherList();
        hourlyAdapter.setData(hourlyWeatherList);
    }

    @Override
    public void onHourlyWeatherDataError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
