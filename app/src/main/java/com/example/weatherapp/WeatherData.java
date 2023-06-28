package com.example.weatherapp;

public class WeatherData {
    private String cityName,weatherDescription,locationTime;
    private double temperature,windSpeed,feelLikeTemp,precipation,gustSpeed,UV;
    private int humidity;

    public WeatherData(String cityName, String locationTime, String weatherDescription, double temperature, double windSpeed, double feelLikeTemp, double precipation, double gustSpeed, int humidity, double UV) {
        this.cityName = cityName;
        this.locationTime = locationTime;
        this.weatherDescription = weatherDescription;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.feelLikeTemp = feelLikeTemp;
        this.precipation = precipation;
        this.gustSpeed = gustSpeed;
        this.humidity = humidity;
        this.UV = UV;
    }

    public String getLocationTime() {
        return locationTime;
    }

    public void setLocationTime(String locationTime) {
        this.locationTime = locationTime;
    }

    public double getFeelLikeTemp() {
        return feelLikeTemp;
    }

    public void setFeelLikeTemp(double feelLikeTemp) {
        this.feelLikeTemp = feelLikeTemp;
    }

    public double getPrecipation() {
        return precipation;
    }

    public void setPrecipation(double precipation) {
        this.precipation = precipation;
    }

    public double getGustSpeed() {
        return gustSpeed;
    }

    public void setGustSpeed(double gustSpeed) {
        this.gustSpeed = gustSpeed;
    }

    public double getUV() {
        return UV;
    }

    public void setUV(int UV) {
        this.UV = UV;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getCityName() {
        return cityName;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }
}
