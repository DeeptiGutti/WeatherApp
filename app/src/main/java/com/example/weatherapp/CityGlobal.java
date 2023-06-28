package com.example.weatherapp;

public class CityGlobal {
    private static CityGlobal instance;
    private String cityName;

    private CityGlobal() {
        // Private constructor to prevent instantiation
    }

    public static synchronized CityGlobal getInstance() {
        if (instance == null) {
            instance = new CityGlobal();
        }
        return instance;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}

