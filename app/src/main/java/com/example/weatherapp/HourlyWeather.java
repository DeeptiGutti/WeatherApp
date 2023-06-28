package com.example.weatherapp;

public class HourlyWeather {
    private String time;
    private double temperature;
    private String condition;

    public HourlyWeather(String time, String temperature, String condition) {
        this.time = time;
        this.temperature = Double.parseDouble(temperature);
        this.condition = condition;
    }

    public String getTime() {
        return time;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getCondition() {
        return condition;
    }
}
