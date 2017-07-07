package ru.bell.gordeev.broker.dao;

import ru.bell.gordeev.broker.domain.Weather;

/**
 * Created by EGordeev on 07.07.2017.
 */

public class CityDaoImpl implements CityDao {
    public boolean isCityKnown(String city) {
        return true;
    }

    public void updateWeatherForCity(Weather localWeather) {

    }
}
