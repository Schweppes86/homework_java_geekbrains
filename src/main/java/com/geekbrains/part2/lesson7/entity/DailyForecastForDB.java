package com.geekbrains.part2.lesson7.entity;

public class DailyForecastForDB {
    private String city;
    private String localDate;
    private double tempMin;
    private double tempMax;
    private String textDay;
    private String textNight;

    public DailyForecastForDB() {
    }

    public String getCity() {
        return city;
    }

    public DailyForecastForDB setCity(String city) {
        this.city = city;
        return this;
    }

    public String getLocalDate() {
        return localDate;
    }

    public DailyForecastForDB setLocalDate(String localDate) {
        this.localDate = localDate;
        return this;
    }

    public double getTempMin() {
        return tempMin;
    }

    public DailyForecastForDB setTempMin(double tempMin) {
        this.tempMin = tempMin;
        return this;
    }

    public double getTempMax() {
        return tempMax;
    }

    public DailyForecastForDB setTempMax(double tempMax) {
        this.tempMax = tempMax;
        return this;
    }

    public String getTextDay() {
        return textDay;
    }

    public DailyForecastForDB setTextDay(String textDay) {
        this.textDay = textDay;
        return this;
    }

    public String getTextNight() {
        return textNight;
    }

    public DailyForecastForDB setTextNight(String textNight) {
        this.textNight = textNight;
        return this;
    }
}

