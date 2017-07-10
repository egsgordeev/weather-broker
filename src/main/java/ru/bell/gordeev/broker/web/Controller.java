package ru.bell.gordeev.broker.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.bell.gordeev.broker.domain.Weather;
import ru.bell.gordeev.broker.service.WeatherService;

/**
 * Created by EGordeev on 10.07.2017.
 */

public class Controller {

    @Autowired
    private WeatherService weatherService;

    @RequestMapping("/index")
    public String showWeather(String city) {
        weatherService.showWeatherForCity(city);
        return "weather";
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateWeather(@ModelAttribute("weather") Weather localWeather, BindingResult result) {
        weatherService.updateWeatherForCity(localWeather);
        return "redirect:/index";
    }
}
