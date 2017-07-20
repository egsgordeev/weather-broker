package ru.bell.gordeev.broker.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bell.gordeev.broker.service.CustomParser;
import ru.bell.gordeev.broker.domain.Weather;

import java.io.IOException;

/**
 * Created by Sovereign on 13.07.2017.
 */
@Service(value = "parser")
class YahooWeatherJsonParser implements CustomParser<Weather> {
    @Autowired
    private ObjectMapper mapper;

    @Override
    public Weather parseToInstance(String values) throws IOException{
        Weather localWeather = new Weather();
        JsonNode tree = mapper.readTree(values).get("query").get("results").get("channel");
        localWeather.setCity(tree.get("location").get("city").asText());
        tree = tree.get("item").get("condition");
        localWeather.setTimeStamp(tree.get("date").asText());
        localWeather.setTemperature(tree.get("temp").asInt());
        localWeather.setText(tree.get("text").asText());
        return localWeather;
    }

    @Override
    public String parseAnInstance(Weather localWeather) throws IOException {
        return mapper.writeValueAsString(localWeather);
    }
}
