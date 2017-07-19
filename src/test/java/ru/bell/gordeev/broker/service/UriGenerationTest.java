package ru.bell.gordeev.broker.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by EGordeev on 18.07.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationTestContext.xml")
public class UriGenerationTest {

    @Autowired
    @Qualifier(value = "uriGenerator")
    UriGenerator uriGenerator;

    @Test
    public void uriGeneration() {
        String keyWord = "Test";
        String requiredQuery = "https://query.yahooapis.com/v1/public/yql?q=" +
                "select item.condition, location.city from weather.forecast " +
                "where woeid in (select woeid from geo.places(1) where text = \"Test\")" +
                "&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        String outputQuery = uriGenerator.generateUriFromKeyWord(keyWord);
        assertEquals("Failed to generate required URI",requiredQuery,outputQuery);
    }

}
