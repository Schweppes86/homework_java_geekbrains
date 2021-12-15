package com.geekbrains.part2.lesson7.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Minimum {
    private int value;

    public Minimum() {
    }

    @JsonGetter("Value")
    public int getValue() {
        return (int) ((value - 32) / 1.8);
    }

    public void setValue(int Value) {
        this.value = Value;
    }

    @Override
    public String toString() {
        return "минимальная " + getValue() + " °C, ";
    }
}
