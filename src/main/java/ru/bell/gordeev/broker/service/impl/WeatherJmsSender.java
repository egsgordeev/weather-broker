package ru.bell.gordeev.broker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import ru.bell.gordeev.broker.domain.Weather;
import ru.bell.gordeev.broker.service.MessageSender;



/**
 * Created by EGordeev on 14.07.2017.
 */
@Service(value = "sender")
class WeatherJmsSender implements MessageSender<Weather> {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(Weather localWeather) {
        jmsTemplate.convertAndSend("jmsQueue", localWeather);
    }  // The destination should be set exactly as it declared in the messaging server
}
