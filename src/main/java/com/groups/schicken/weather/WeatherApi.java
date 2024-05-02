package com.groups.schicken.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/weathers")
public class WeatherApi {
    private final WeatherService weatherService;

    @GetMapping("/sync")
    public void syncWeatherDataList() throws Exception {
        weatherService.syncWeatherDataList();
    }

    @GetMapping("/sync/now")
    public void syncWeatherData() throws Exception {
        weatherService.syncWeatherData();
    }

    @GetMapping
    public List<OpenWeather> getWeatherList() throws Exception {
        return weatherService.getWeatherList();
    }

    @GetMapping("/now")
    public OpenWeather getWeather() throws Exception {
        return weatherService.getWeather();
    }
}
