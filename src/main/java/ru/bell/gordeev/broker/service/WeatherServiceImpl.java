package ru.bell.gordeev.broker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bell.gordeev.broker.dao.CityDao;
import ru.bell.gordeev.broker.domain.Weather;

/**
 * Created by Sovereign on 09.07.2017.
 */
@Service(value = "serv")
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    private CityDao cityDao;

    @Transactional
    public void updateWeatherForCity(Weather localWeather) {
        recognizeWeather(localWeather);
        cityDao.updateWeatherForCity(localWeather);
    }

    @Transactional
    public Weather showWeatherForCity(String city) {
        return cityDao.showWeatherForCity(city);
    }

    private Weather recognizeWeather(Weather localWeather) { //method structure for the web testing stage
        localWeather.setTimeStamp("webapp day");
        localWeather.setText("Sunny");
        localWeather.setTemperature(60);
        return localWeather;
    }
}
