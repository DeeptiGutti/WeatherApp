package com.example.weatherapp;

public class ForecastData {
    private final double avgHumidity;
    private final int willItRain;
    private final int chanceOfRain;
    private final int chanceOfSnow;
    private String date;
    private int dateEpoch;
    private double maxTempC;
    private double maxTempF;
    private double minTempC;
    private double minTempF;
    private double avgTempC;

    private double maxWindKPH;
    private double totalPrecipMM;

    private String weatherConditionText;
    private String weatherConditionIcon;
    private int weatherConditionCode;
    private double uvIndex;

    public ForecastData(String date, int dateEpoch, double maxTempC,double minTempC,
                        double avgTempC, double totalPrecipMM,
                        double avgHumidity, int willItRain, int chanceOfRain, int chanceOfSnow,
                        String weatherConditionText, String weatherConditionIcon, int weatherConditionCode, double uvIndex) {
        this.date = date;
        this.dateEpoch = dateEpoch;
        this.maxTempC = maxTempC;
        this.minTempC = minTempC;
        this.avgTempC = avgTempC;
        this.totalPrecipMM = totalPrecipMM;
        this.avgHumidity = avgHumidity;
        this.willItRain = willItRain;
        this.chanceOfRain = chanceOfRain;
        this.chanceOfSnow = chanceOfSnow;
        this.weatherConditionText = weatherConditionText;
        this.weatherConditionIcon = weatherConditionIcon;
        this.weatherConditionCode = weatherConditionCode;
        this.uvIndex = uvIndex;
    }

    // Add getter methods for all the fields

    public double getAvgHumidity() {
        return avgHumidity;
    }

    public int getWillItRain() {
        return willItRain;
    }

    public int getChanceOfRain() {
        return chanceOfRain;
    }

    public int getChanceOfSnow() {
        return chanceOfSnow;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDateEpoch() {
        return dateEpoch;
    }

    public void setDateEpoch(int dateEpoch) {
        this.dateEpoch = dateEpoch;
    }

    public void setMaxTempC(double maxTempC) {
        this.maxTempC = maxTempC;
    }

    public double getMaxTempF() {
        return maxTempF;
    }

    public void setMaxTempF(double maxTempF) {
        this.maxTempF = maxTempF;
    }

    public double getMinTempC() {
        return minTempC;
    }

    public void setMinTempC(double minTempC) {
        this.minTempC = minTempC;
    }

    public double getMinTempF() {
        return minTempF;
    }

    public void setMinTempF(double minTempF) {
        this.minTempF = minTempF;
    }

    public double getAvgTempC() {
        return avgTempC;
    }

    public void setAvgTempC(double avgTempC) {
        this.avgTempC = avgTempC;
    }

    public double getMaxWindKPH() {
        return maxWindKPH;
    }

    public void setMaxWindKPH(double maxWindKPH) {
        this.maxWindKPH = maxWindKPH;
    }

    public double getTotalPrecipMM() {
        return totalPrecipMM;
    }

    public void setTotalPrecipMM(double totalPrecipMM) {
        this.totalPrecipMM = totalPrecipMM;
    }

    public void setWeatherConditionText(String weatherConditionText) {
        this.weatherConditionText = weatherConditionText;
    }

    public String getWeatherConditionIcon() {
        return weatherConditionIcon;
    }

    public void setWeatherConditionIcon(String weatherConditionIcon) {
        this.weatherConditionIcon = weatherConditionIcon;
    }

    public int getWeatherConditionCode() {
        return weatherConditionCode;
    }

    public void setWeatherConditionCode(int weatherConditionCode) {
        this.weatherConditionCode = weatherConditionCode;
    }

    public double getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(double uvIndex) {
        this.uvIndex = uvIndex;
    }

    public String getDate() {
        return date;
    }


    public double getMaxTempC() {
        return maxTempC;
    }


    public String getWeatherConditionText() {
        return weatherConditionText;
    }

}
