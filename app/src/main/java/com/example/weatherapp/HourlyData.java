package com.example.weatherapp;

import com.example.weatherapp.HourlyWeather;

import java.util.List;

public class HourlyData {

    private List<HourlyWeather> hourlyWeatherList;

    public HourlyData(List<HourlyWeather> hourlyWeatherList) {
        this.hourlyWeatherList = hourlyWeatherList;
    }

    public List<HourlyWeather> getHourlyWeatherList() {
        return hourlyWeatherList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (HourlyWeather hourlyWeather : hourlyWeatherList) {
            builder.append("Time: ").append(hourlyWeather.getTime()).append("\n");
            builder.append("Temperature: ").append(hourlyWeather.getTemperature()).append("\n");
            builder.append("Condition: ").append(hourlyWeather.getCondition()).append("\n");
            builder.append("\n");
        }

        return builder.toString();
    }
}
