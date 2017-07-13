package ru.bell.gordeev.broker.service.impl;

import org.springframework.stereotype.Service;
import ru.bell.gordeev.broker.service.UriGenerator;

/**
 * Created by EGordeev on 13.07.2017.
 */
@Service(value = "uriGen")
public class UriGeneratorImpl implements UriGenerator{
    @Override
    public String generateUriFromKeyWord(String keyWord) {

        return "https://query.yahooapis.com/v1/public/yql?q=" +
                "select item.condition, location.city from weather.forecast " +
                "where woeid in (select woeid from geo.places(1) where text = \""+keyWord+"\")" +
                "&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
    }
}
