package ru.bell.gordeev.broker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bell.gordeev.broker.dao.CityDao;
import ru.bell.gordeev.broker.domain.Weather;

/**
 * Created by Sovereign on 09.07.2017.
 */
@Service
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    private CityDao cityDao;

    @Transactional
    public void updateWeatherForCity(Weather localWeather) {
        cityDao.updateWeatherForCity(localWeather);
    }

    @Transactional
    public Weather showWeatherForCity(String city) {
        return cityDao.showWeatherForCity(city);
    }
}
