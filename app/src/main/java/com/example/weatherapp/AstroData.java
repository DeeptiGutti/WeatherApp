package com.example.weatherapp;

public class AstroData {
    private String sunrise;
    private String sunset;
    private String moonrise;
    private String moonset;
    private String moonPhase;
    private String moonIllumination;
    private int isMoonUp;
    private int isSunUp;

    public AstroData(String sunrise, String sunset, String moonrise, String moonset, String moonPhase,
                     String moonIllumination, int isMoonUp, int isSunUp) {
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.moonrise = moonrise;
        this.moonset = moonset;
        this.moonPhase = moonPhase;
        this.moonIllumination = moonIllumination;
        this.isMoonUp = isMoonUp;
        this.isSunUp = isSunUp;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getMoonrise() {
        return moonrise;
    }

    public String getMoonset() {
        return moonset;
    }

    public String getMoonPhase() {
        return moonPhase;
    }

    public String getMoonIllumination() {
        return moonIllumination;
    }

    public int isMoonUp() {
        return isMoonUp;
    }

    public int isSunUp() {
        return isSunUp;
    }
}
