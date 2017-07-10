package ru.bell.gordeev.broker.service;

import ru.bell.gordeev.broker.domain.Weather;

/**
 * Created by EGordeev on 09.07.2017.
 */
public interface WeatherService {
    void updateWeatherForCity(Weather localWeather);

    Weather showWeatherForCity(String city);
}
