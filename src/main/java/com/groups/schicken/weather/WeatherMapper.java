package com.groups.schicken.weather;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WeatherMapper {
    int insertWeather(OpenWeather openWeather) throws Exception;
    List<OpenWeather> getWeatherList(OpenWeather openWeather) throws Exception;

    OpenWeather getWeather(OpenWeather openWeather) throws Exception;
}
