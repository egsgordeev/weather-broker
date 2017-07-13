package ru.bell.gordeev.broker.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bell.gordeev.broker.domain.Weather;
import ru.bell.gordeev.broker.service.WeatherService;

/**
 * Created by EGordeev on 10.07.2017.
 */

@Controller
public class CentralController {

    @Autowired
    private WeatherService weatherService;

    @PostMapping("/")
    public String updateWeather(@ModelAttribute("localWeather") Weather localWeather) {
        weatherService.updateWeatherForCity(localWeather);
        return "weather";
    }

    @GetMapping("/")
    public String weatherForm(Model model) {
        model.addAttribute("localWeather", new Weather());
        return "weather";
    }
}
