package ru.bell.gordeev.broker.dao;

import ru.bell.gordeev.broker.domain.Weather;

import java.util.List;

/**
 * Created by EGordeev on 07.07.2017.
 */

public interface CityDao {
    void updateWeatherForCity(Weather localWeather);

    boolean isCityKnown(String city);
}
