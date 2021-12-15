package com.geekbrains.part2.lesson7;

import com.geekbrains.part2.lesson7.enums.Periods;

import java.io.IOException;

public interface WeatherProvider {

    void getWeather(Periods periods) throws IOException;

}
