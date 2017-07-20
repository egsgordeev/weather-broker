package ru.bell.gordeev.broker.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import ru.bell.gordeev.broker.service.MessageReceiver;
import ru.bell.gordeev.broker.dao.CityDao;
import ru.bell.gordeev.broker.domain.Weather;

import java.io.IOException;

/**
 * Created by EGordeev on 14.07.2017.
 */
@Service(value = "recv")
class WeatherJmsReceiver implements MessageReceiver<String> {

    @Autowired
    private CityDao cityDao;

    @Autowired
    private ObjectMapper mapper;

    @Override
    @JmsListener(destination = "jmsQueue", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String receivedWeather) throws IOException {
        Weather localWeather = mapper.readValue(receivedWeather, Weather.class);
        cityDao.updateWeatherForCity(localWeather);
    } // The destination should be set exactly as it declared in the messaging server
}
