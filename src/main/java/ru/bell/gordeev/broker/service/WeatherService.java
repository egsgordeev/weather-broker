package ru.bell.gordeev.broker.service;

import ru.bell.gordeev.broker.domain.Weather;

import java.io.IOException;

/**
 * Created by EGordeev on 09.07.2017.
 */
public interface WeatherService {
    void updateWeatherForCity(String city) throws IOException;

    Weather showWeatherForCity(String city);
}
