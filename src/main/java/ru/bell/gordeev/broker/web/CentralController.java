package ru.bell.gordeev.broker.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.bell.gordeev.broker.service.WeatherService;

import java.io.IOException;

/**
 * Created by EGordeev on 10.07.2017.
 */

@Controller
public class CentralController {

    @Autowired
    private WeatherService weatherService;

    @PostMapping("/")
    public String updateWeather(@RequestParam("city") String city) {
        try {
            weatherService.updateWeatherForCity(city);
            return "weather";
        } catch (IOException ex) {
            return ex.toString(); //CHANGE!
        }
    }

    @GetMapping("/")
    public String weatherForm() {
        return "weather";
    }
}
