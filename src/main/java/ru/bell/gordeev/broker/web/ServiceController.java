package ru.bell.gordeev.broker.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bell.gordeev.broker.domain.Weather;
import ru.bell.gordeev.broker.service.WeatherService;

/**
 * Created by EGordeev on 14.07.2017.
 */
@RestController
public class ServiceController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/res")
    public Weather showWeather(@RequestParam(value = "city") String city) {
        return weatherService.showWeatherForCity(city);
    }

}
