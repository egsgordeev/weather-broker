package ru.bell.gordeev.broker.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestOperations;
import ru.bell.gordeev.broker.dao.CityDao;
import ru.bell.gordeev.broker.domain.Weather;
import ru.bell.gordeev.broker.service.CustomParser;
import ru.bell.gordeev.broker.service.MessageSender;
import ru.bell.gordeev.broker.service.UriGenerator;

import java.io.*;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by EGordeev on 19.07.2017.
 */

@Configuration(value = "testConfig")
public class TestingSpringJavaConfig {

    @Bean
    public Weather requiredLocalWeather() {
        return new Weather(76, "Saratov", "Tue, 18 Jul 2017 05:00 PM MSK", "Sunny");
    }

    @Bean
    @Primary
    public CityDao cityDao() {
        return mock(CityDao.class);
    }

    @Bean
    @Primary
    public MessageSender sender() {
        return mock(MessageSender.class);
    }

    @Bean
    @Primary
    public RestOperations rest() {
       RestOperations restMock =  mock(RestOperations.class);
        when(restMock.getForObject(eq("UriTest"), eq(String.class))).thenReturn("ResponseTest");
        return restMock;
    }

    @Bean
    @Primary
    public UriGenerator uriGen() {
        UriGenerator uriMock =  mock(UriGenerator.class);
        when(uriMock.generateUriFromKeyWord(eq("Test"))).thenReturn("UriTest");
        return uriMock;
    }

    @Bean
    @Primary
    public CustomParser<? extends Weather> parser() throws IOException{
        CustomParser parserMock = mock(CustomParser.class);
        when(parserMock.parseToInstance(eq("ResponseTest"))).thenReturn(requiredLocalWeather());
        return parserMock;
    }

    @Bean
    public String responseJson() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/responseJson.json"))) {
            StringBuilder responseJson = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                responseJson.append(line);
                line = reader.readLine();
            }
            return responseJson.toString();
        }
    }
}
