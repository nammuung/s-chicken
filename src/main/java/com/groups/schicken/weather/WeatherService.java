package com.groups.schicken.weather;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherMapper weatherMapper;
    @Value("${weather.api.key}")
    private String apiKey;
    @Value("${weather.lat}")
    private String lat;
    @Value("${weather.lon}")
    private String lon;


    @Scheduled(cron="0 0 * * * *", zone = "Asia/Seoul")
    public void syncWeatherData() {
        String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        try {
//            urlBuilder.append("?" + URLEncoder.encode("q", "UTF-8") + "=Koesan");
            urlBuilder.append("?" + URLEncoder.encode("appid", "UTF-8") + "=" + apiKey);
            urlBuilder.append("&" + URLEncoder.encode("lang", "UTF-8") + "=kr");
            urlBuilder.append("&" + URLEncoder.encode("units", "UTF-8") + "=metric");
            urlBuilder.append("&" + URLEncoder.encode("lat", "UTF-8") + "=" + lat);
            urlBuilder.append("&" + URLEncoder.encode("lon", "UTF-8") + "=" + lon);
            System.out.println("urlBuilder.toString() = " + urlBuilder.toString());
            RestTemplate restTemplate = new RestTemplate();
            OpenWeather response = restTemplate.getForObject(urlBuilder.toString(), OpenWeather.class);
            weatherMapper.insertWeather(response);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Scheduled(cron="0 30 * * * *", zone = "Asia/Seoul")
    public void syncWeatherDataList() {
        String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast";
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        try {
//            urlBuilder.append("?" + URLEncoder.encode("q", "UTF-8") + "=Koesan");
            urlBuilder.append("?" + URLEncoder.encode("appid", "UTF-8") + "=" + apiKey);
            urlBuilder.append("&" + URLEncoder.encode("lang", "UTF-8") + "=kr");
            urlBuilder.append("&" + URLEncoder.encode("units", "UTF-8") + "=metric");
            urlBuilder.append("&" + URLEncoder.encode("lat", "UTF-8") + "=" + lat);
            urlBuilder.append("&" + URLEncoder.encode("lon", "UTF-8") + "=" + lon);
            System.out.println("urlBuilder.toString() = " + urlBuilder.toString());
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(urlBuilder.toString(), String.class);
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(response.getBody());
            JSONArray list = (JSONArray) data.get("list");
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            List<OpenWeather> weathers = mapper.readValue(list.toString(), new TypeReference<List<OpenWeather>>() {});
            for(OpenWeather weather : weathers){
                weatherMapper.insertWeather(weather);
                System.out.println("weather = " + weather);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<OpenWeather> getWeatherList() throws Exception {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        OpenWeather openWeather = new OpenWeather();
        openWeather.setDt(System.currentTimeMillis()/1000);
        return weatherMapper.getWeatherList(openWeather);
    }

    public OpenWeather getWeather() throws Exception {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        OpenWeather openWeather = new OpenWeather();
        openWeather.setDt(System.currentTimeMillis()/1000);
        return weatherMapper.getWeather(openWeather);
    }
}
