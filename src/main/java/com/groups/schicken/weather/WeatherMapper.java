package com.groups.schicken.weather;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WeatherMapper {
    int insertWeather(OpenWeather openWeather) throws Exception;
}
