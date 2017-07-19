package ru.bell.gordeev.broker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.bell.gordeev.broker.domain.Weather;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by EGordeev on 18.07.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationTestContext.xml")
public class JsonParsingTest {

    @Autowired
    @Qualifier(value = "jsonParser")
    CustomParser<? extends Weather> jsonParser1;

    @Autowired
    @Qualifier(value = "jsonParser")
    CustomParser<? super Weather> jsonParser2;

    @Autowired
    private ObjectMapper entityMapper;

    @Autowired
    Weather requiredLocalWeather;

    @Autowired
    String responseJson;

    private boolean complexEquals(Weather required, Weather acquired) {
        return (required.getTemperature()==acquired.getTemperature())&&
                (required.getCity().equals(acquired.getCity()))&&
                (required.getText().equals(acquired.getText()))&&
                (required.getTimeStamp().equals(acquired.getTimeStamp()));
    }

    @Test
    public void parsingToInstance() throws IOException {
        Weather outputWeather = jsonParser1.parseToInstance(responseJson);
        assertTrue("Parsing to an instance failed",complexEquals(requiredLocalWeather,outputWeather));
    }

    @Test
    public void parsingFromInstance() throws IOException {
        assertEquals("Parsing from an instance failed",
                entityMapper.writeValueAsString(requiredLocalWeather),
                jsonParser2.parseAnInstance(requiredLocalWeather));
    }
}
