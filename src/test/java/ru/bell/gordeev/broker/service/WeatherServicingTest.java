package ru.bell.gordeev.broker.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestOperations;
import ru.bell.gordeev.broker.dao.CityDao;
import ru.bell.gordeev.broker.domain.Weather;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;



/**
 * Created by EGordeev on 18.07.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationTestContext.xml")
public class WeatherServicingTest {
    @Autowired
    WeatherService service;

    @Autowired
    Weather requiredLocalWeather;

    @Mock
    @Autowired
    CityDao cityDao;

    @Mock
    @Autowired
    MessageSender<? super Weather> sender;

    @Mock
    @Autowired
    RestOperations rest;

    @Mock
    @Autowired
    UriGenerator uriGen;

    @Mock
    @Autowired
    CustomParser<? extends Weather> parser;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

    @Test
    public void showingTesting() {
        String cityTestName = "Test";
        service.showWeatherForCity(cityTestName);
        verify(cityDao).showWeatherForCity(stringArgumentCaptor.capture());
        assertTrue("Query is transferred to DB with a different parameter",
                cityTestName.equalsIgnoreCase(stringArgumentCaptor.getValue()));
    }

    @Test
    public void updatingTesting() throws IOException {
        String cityTestName = "Test";
        service.updateWeatherForCity(cityTestName);
        verify(uriGen).generateUriFromKeyWord(cityTestName);
        verify(rest).getForObject(stringArgumentCaptor.capture(), eq(String.class));
        assertNotNull("Failed to pass URI correctly",stringArgumentCaptor.getValue());
        verify(parser).parseToInstance(stringArgumentCaptor.capture());
        assertNotNull("Failed to pass REST response correctly",stringArgumentCaptor.getValue());
        verify(sender).sendMessage(requiredLocalWeather);
    }
}

