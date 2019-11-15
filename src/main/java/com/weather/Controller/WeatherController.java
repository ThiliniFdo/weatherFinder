/**
 * WeatherController controller for maintain API endpoints
 *
 * @version 1.0
 * @author Thilini Fernando
 */

package com.weather.Controller;

import com.weather.Entity.City;
import com.weather.Entity.Weather;
import com.weather.Service.CityService;
import com.weather.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private CityService cityService;


    /**
     * GET request for home page
     * @param model
     * @return home view
     */
    @GetMapping("/weather")
    public String getWeather(Model model) {
        model.addAttribute("cities", cityService.getCities());
        model.addAttribute("city", new City());
        return "home";
    }

    /**
     * POST request for home page submission
     * @param model
     * @param city
     * @return weather view
     */
    @PostMapping("/weather")
    public String submitWeather(Model model, @ModelAttribute City city) {
        Weather weather = weatherService.getData(cityService.getCity(city));
        if (weather != null) {
            model.addAttribute("today", weatherService.getToday(weather));
            model.addAttribute("tomorrow", weatherService.getTomorrow(weather));
        }
        return "weather";
    }

}
