package com.example.weatherapp;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherAPICall {

    private static WeatherAPICall instance;
    private RequestQueue requestQueue;
    private Context context;
    private String key = "400ae0732fbf40c18e1204402232206";

    private WeatherAPICall(Context context) {
        this.context = context.getApplicationContext();
        requestQueue = getRequestQueue();
    }

    public static synchronized WeatherAPICall getInstance(Context context) {
        if (instance == null) {
            instance = new WeatherAPICall(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    public void fetchWeatherData(String cityName, final WeatherDataListener listener) {
        String API_URL = "http://api.weatherapi.com/v1/forecast.json?key=" + key + "&q=" + cityName + "&days=1&aqi=no&alerts=no";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        WeatherData weatherData = parseWeatherData(response);
                        if (weatherData != null) {
                            listener.onWeatherDataReceived(weatherData);
                        } else {
                            listener.onWeatherDataError("Failed to parse weather data");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse != null && error.networkResponse.statusCode == 400) {
                            Toast.makeText(context, "Bad request", Toast.LENGTH_LONG).show();

                        } else {
                            // Handle other error cases
                        }
                        listener.onWeatherDataError("Failed to fetch weather data: " + error.getMessage());
                    }

                });

        getRequestQueue().add(jsonObjectRequest);
    }


    private WeatherData parseWeatherData(JSONObject response) {
        try {
            JSONObject locationObject = response.getJSONObject("location");
            String cityName = locationObject.getString("name");
            String locationTime = locationObject.getString("localtime");

            JSONObject currentObject = response.getJSONObject("current");
            double temperature = currentObject.getDouble("temp_c");
            String weatherDescription = currentObject.getJSONObject("condition").getString("text");
            int humidity = currentObject.getInt("humidity");
            double windSpeed = currentObject.getDouble("wind_kph");
            double feelLikeTemp = currentObject.getDouble("feelslike_c");
            double precipation = currentObject.getDouble("precip_mm");
            int UV = currentObject.getInt("uv");
            double gustSpeed = currentObject.getDouble("gust_kph");
             return new WeatherData(cityName,locationTime,weatherDescription, temperature, windSpeed, feelLikeTemp, precipation, gustSpeed,humidity,UV);



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public interface WeatherDataListener {
        void onWeatherDataReceived(WeatherData weatherData);

        void onWeatherDataError(String errorMessage);
    }

    public void fetchForecastData(String cityName, final ForecastDataListener listener) {
        String API_URL = "http://api.weatherapi.com/v1/forecast.json?key=" + key + "&q=" + cityName + "&days=1&aqi=no&alerts=no";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject forecastObject = response.getJSONObject("forecast");
                            JSONArray forecastdayArray = forecastObject.getJSONArray("forecastday");

                            if (forecastdayArray.length() > 0) {
                                JSONObject forecastdayObject = forecastdayArray.getJSONObject(0);

                                String date = forecastdayObject.getString("date");
                                int dateEpoch = forecastdayObject.getInt("date_epoch");

                                JSONObject dayObject = forecastdayObject.getJSONObject("day");
                                double maxTempC = dayObject.getDouble("maxtemp_c");
                                double minTempC = dayObject.getDouble("mintemp_c");
                                double avgTempC = dayObject.getDouble("avgtemp_c");
                                double totalPrecipMM = dayObject.getDouble("totalprecip_mm");
                                double totalSnowCM = dayObject.getDouble("totalsnow_cm");
                                double avgHumidity = dayObject.getDouble("avghumidity");

                                int chanceOfRain = dayObject.getInt("daily_chance_of_rain");

                                int chanceOfSnow = dayObject.getInt("daily_chance_of_snow");

                                JSONObject conditionObject = dayObject.getJSONObject("condition");
                                String weatherConditionText = conditionObject.getString("text");
                                String weatherConditionIcon = conditionObject.getString("icon");
                                int weatherConditionCode = conditionObject.getInt("code");

                                double uvIndex = dayObject.getDouble("uv");

                                ForecastData forecastData = new ForecastData(date, dateEpoch, maxTempC,  minTempC,avgTempC, totalPrecipMM, totalSnowCM, (int) avgHumidity,
                                        chanceOfRain, chanceOfSnow, weatherConditionText, weatherConditionIcon, weatherConditionCode, uvIndex);

                                listener.onForecastDataReceived(forecastData);
                            } else {
                                listener.onForecastDataError("No forecast data available");
                            }
                        } catch (JSONException e) {
                            listener.onForecastDataError("Failed to parse JSON response");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse != null && error.networkResponse.statusCode == 400) {
                            Toast.makeText(context, "Bad request", Toast.LENGTH_LONG).show();

                        } else {
                            // Handle other error cases
                        }
                        listener.onForecastDataError("Failed to fetch forecast data: " + error.getMessage());
                    }
                });

        getRequestQueue().add(jsonObjectRequest);
    }

    public interface ForecastDataListener {
        void onForecastDataReceived(ForecastData forecastData);

        void onForecastDataError(String errorMessage);
    }
    public void fetchAstroData(String cityName, final AstroDataListener listener) {
        String API_URL = "http://api.weatherapi.com/v1/forecast.json?key=" + key + "&q=" + cityName + "&days=1&aqi=no&alerts=no";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject forecastObject = response.getJSONObject("forecast");
                            JSONArray forecastdayArray = forecastObject.getJSONArray("forecastday");

                            if (forecastdayArray.length() > 0) {
                                JSONObject forecastdayObject = forecastdayArray.getJSONObject(0);

                                JSONObject astroObject = forecastdayObject.getJSONObject("astro");
                                String sunrise = astroObject.getString("sunrise");
                                String sunset = astroObject.getString("sunset");
                                String moonrise = astroObject.getString("moonrise");
                                String moonset = astroObject.getString("moonset");
                                String moonPhase = astroObject.getString("moon_phase");
                                String moonIllumination = astroObject.getString("moon_illumination");
                                int isMoonUp = astroObject.getInt("is_moon_up");
                                int isSunUp = astroObject.getInt("is_sun_up");

                                AstroData astroData = new AstroData(sunrise, sunset, moonrise, moonset, moonPhase,
                                        moonIllumination, isMoonUp, isSunUp);

                                listener.onAstroDataReceived(astroData);
                            } else {
                                listener.onAstroDataError("No astro data available");
                            }
                        } catch (JSONException e) {
                            listener.onAstroDataError("Failed to parse JSON response");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse != null && error.networkResponse.statusCode == 400) {
                            Toast.makeText(context, "Bad request", Toast.LENGTH_LONG).show();

                        } else {
                            // Handle other error cases
                        }
                        listener.onAstroDataError("Failed to fetch astro data: " + error.getMessage());
                    }
                });

        getRequestQueue().add(jsonObjectRequest);
    }


    public interface AstroDataListener {
        void onAstroDataReceived(AstroData astroData);
        void onAstroDataError(String errorMessage);
    }
    public void fetchHourlyData(String cityName, final HourlyWeatherDataListener listener) {
        String API_URL = "http://api.weatherapi.com/v1/forecast.json?key=" + key + "&q=" + cityName + "&days=1&aqi=no&alerts=no";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject forecastObject = response.getJSONObject("forecast");
                            JSONObject forecastdayObject = forecastObject.getJSONArray("forecastday").getJSONObject(0);
                            JSONArray hourlyArray = forecastdayObject.getJSONArray("hour");

                            if (hourlyArray.length() > 0) {
                                List<HourlyWeather> hourlyWeatherList = new ArrayList<>();

                                for (int i = 0; i < hourlyArray.length(); i++) {
                                    JSONObject hourlyObject = hourlyArray.getJSONObject(i);
                                    String time = hourlyObject.getString("time");
                                    String temperature = hourlyObject.getString("temp_c");
                                    String condition = hourlyObject.getJSONObject("condition").getString("text");

                                    HourlyWeather hourlyWeather = new HourlyWeather(time, temperature, condition);
                                    hourlyWeatherList.add(hourlyWeather);
                                }

                                HourlyData hourlyData = new HourlyData(hourlyWeatherList);
                                listener.onHourlyWeatherDataReceived(hourlyData);
                            } else {
                                listener.onHourlyWeatherDataError("No hourly data available");
                            }
                        } catch (JSONException e) {
                            listener.onHourlyWeatherDataError("Failed to parse JSON response");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse != null && error.networkResponse.statusCode == 400) {
                            Toast.makeText(context, "Bad request", Toast.LENGTH_LONG).show();

                        } else {
                            // Handle other error cases
                        }
                        listener.onHourlyWeatherDataError("Failed to fetch hourly data: " + error.getMessage());
                    }
                });

        getRequestQueue().add(jsonObjectRequest);
    }
    public interface HourlyWeatherDataListener {
        void onHourlyWeatherDataReceived(HourlyData hourlyData);
        void onHourlyWeatherDataError(String errorMessage);
    }



}









