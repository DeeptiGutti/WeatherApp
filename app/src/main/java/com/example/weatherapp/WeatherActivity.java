package com.example.weatherapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherActivity extends AppCompatActivity {

    private TextView cityNameTextView,temperatureTextView,weatherDescriptionTextView,
            humidityTextView,windSpeedTextView,dateTextView,uVTextView,precipitationTextView,feelLikeTextView,gustSpeedtextView;

    private LinearLayout rootLayout ;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weather);
        rootLayout = findViewById(R.id.rootLayout);
        CityGlobal cityGlobal = CityGlobal.getInstance();
        String cityName = cityGlobal.getCityName();
        Log.e(TAG, "CityName: " + cityName);



        cityNameTextView = findViewById(R.id.textViewCityName);
        temperatureTextView = findViewById(R.id.textViewTemperature);
        weatherDescriptionTextView = findViewById(R.id.textViewWeatherDescription);
        humidityTextView = findViewById(R.id.textViewHumidity);
        windSpeedTextView = findViewById(R.id.textViewWindSpeed);
        dateTextView = findViewById(R.id.textViewLocalTime);
        uVTextView = findViewById(R.id.textViewUV);
        precipitationTextView = findViewById(R.id.textViewPrecipMm);
        feelLikeTextView = findViewById(R.id.textViewFeelsLikeC);
        gustSpeedtextView =findViewById(R.id.textViewGustWind);


        Button currentButton = findViewById(R.id.idCurrentButton);
        Button forecastButton = findViewById(R.id.idForecastButton);
        Button astroButton = findViewById(R.id.idAstroButton);
        Button hourlyButton = findViewById(R.id.idHourlyButton);
        Button changeCity = findViewById(R.id.buttonChangeLocation);

        GlobalButtonActivty globalButtonActivty = new GlobalButtonActivty();


        currentButton.setOnClickListener(globalButtonActivty);
        forecastButton.setOnClickListener(globalButtonActivty);
        astroButton.setOnClickListener(globalButtonActivty);
        hourlyButton.setOnClickListener(globalButtonActivty);


        currentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cityName != null && !cityName.isEmpty()) {
                    WeatherAPICall weatherAPICall = WeatherAPICall.getInstance(context);
                    weatherAPICall.fetchWeatherData(cityName, new WeatherAPICall.WeatherDataListener() {
                        @Override
                        public void onWeatherDataReceived(WeatherData weatherData) {
                            updateUI(weatherData);
                        }

                        @Override
                        public void onWeatherDataError(String errorMessage) {
                            // Handle the error
                        }
                    });
                } else {
                    Toast.makeText(context, "City name is not available", Toast.LENGTH_SHORT).show();
                }
            }
        });



        changeCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WeatherActivity.this);
                builder.setTitle("Enter City Name");

                // Create EditText for user input
                final EditText input = new EditText(WeatherActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String cityName = input.getText().toString();
                        CityGlobal cityGlobal = CityGlobal.getInstance();
                        cityGlobal.setCityName(cityName);
                        Log.e(TAG, "CityName: " + cityName);

                        WeatherAPICall weatherAPICall = WeatherAPICall.getInstance(context);
                        weatherAPICall.fetchWeatherData(cityName, new WeatherAPICall.WeatherDataListener() {
                            @Override
                            public void onWeatherDataReceived(WeatherData weatherData) {
                                updateUI(weatherData);
                            }

                            @Override
                            public void onWeatherDataError(String errorMessage) {
                                // Handle the error
                            }
                        });
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });




        context = this; // Assign the context

        WeatherAPICall weatherAPICall = WeatherAPICall.getInstance(context);
        weatherAPICall.fetchWeatherData(cityName, new WeatherAPICall.WeatherDataListener() {
            @Override
            public void onWeatherDataReceived(WeatherData weatherData) {
                updateUI(weatherData);
            }

            @Override
            public void onWeatherDataError(String errorMessage) {
                // Handle the error
            }
        });
    }


    private void updateUI(WeatherData weatherData) {
        cityNameTextView.setText(weatherData.getCityName());

        temperatureTextView.setText(String.valueOf(weatherData.getTemperature())+"°C");
        weatherDescriptionTextView.setText(weatherData.getWeatherDescription());
        dateTextView.setText("Date: "+String.valueOf(weatherData.getLocationTime()));
        Log.e(TAG, "updateUI: "+String.valueOf(weatherData.getLocationTime()));
        uVTextView.setText("UV: "+String.valueOf(weatherData.getUV()));
        precipitationTextView.setText("Precipitation: "+String.valueOf(weatherData.getPrecipation()));
        feelLikeTextView.setText("Feels like: "+String.valueOf(weatherData.getFeelLikeTemp())+"°C");
        gustSpeedtextView.setText("Gust Speed: "+String.valueOf(weatherData.getGustSpeed()));
        humidityTextView.setText(String.valueOf(weatherData.getHumidity()));
        windSpeedTextView.setText(String.valueOf(weatherData.getWindSpeed()));
    }
}
