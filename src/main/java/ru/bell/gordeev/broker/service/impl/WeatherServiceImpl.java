package ru.bell.gordeev.broker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestOperations;
import ru.bell.gordeev.broker.service.WeatherService;
import ru.bell.gordeev.broker.dao.CityDao;
import ru.bell.gordeev.broker.domain.Weather;
import ru.bell.gordeev.broker.service.CustomParser;
import ru.bell.gordeev.broker.service.MessageSender;
import ru.bell.gordeev.broker.service.UriGenerator;


import java.io.IOException;

/**
 * Created by EGordeev on 09.07.2017.
 */
@Service(value = "serv")
class WeatherServiceImpl implements WeatherService {
    @Autowired
    private CityDao cityDao;

    @Autowired
    private MessageSender<? super Weather> sender;

    @Autowired
    private RestOperations rest;

    @Autowired
    private UriGenerator uriGen;

    @Autowired
    private CustomParser<? extends Weather> parser;

    @Transactional(rollbackFor = Exception.class)
    public void updateWeatherForCity (String city) throws IOException {
        sender.sendMessage(recognizeWeather(city));
    }

    @Transactional
    public Weather showWeatherForCity(String city) {
        return cityDao.showWeatherForCity(city);
    }

    private Weather recognizeWeather(String city) throws IOException {
        String uri = uriGen.generateUriFromKeyWord(city);
        String response = rest.getForObject(uri, String.class);
        return parser.parseToInstance(response);
    }
}
