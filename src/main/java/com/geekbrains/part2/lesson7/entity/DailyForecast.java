package com.geekbrains.part2.lesson7.entity;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyForecast {
    private String date;
    Temperature temperatureObject;
    Day dayObject;
    Night nightObject;

    public DailyForecast() {
    }

    @JsonGetter ("Date")
    public String getDate() {
        return date;
    }

    @JsonGetter ("Temperature")
    public Temperature getTemperatureObject() {
        return temperatureObject;
    }

    @JsonGetter ("Day")
    public Day getDayObject() {
        return dayObject;
    }

    @JsonGetter ("Night")
    public Night getNightObject() {
        return nightObject;
    }

    public void setDate(String date) {
        String dateShort = date.substring(0,10);
        this.date = dateShort;
    }

    public void setTemperatureObject(Temperature temperatureObject) {
        this.temperatureObject = temperatureObject;
    }

    public void setDayObject(Day dayObject) {
        this.dayObject = dayObject;
    }

    public void setNightObject(Night nightObject) {
        this.nightObject = nightObject;
    }

//    public Double getAvgCelsiusTemp() {
//
//        Double avgCelsiusTemp = ((((Double.parseDouble(temperatureObject.getMaximum().toString()) +
//                Double.parseDouble(temperatureObject.getMinimum().toString())) / 2) - 32) / 1.8);
//        return avgCelsiusTemp;
//    }

    @Override
    public String toString() {
        return '\n' + "Прогноз на " + date + temperatureObject + dayObject + nightObject;
    }

}
