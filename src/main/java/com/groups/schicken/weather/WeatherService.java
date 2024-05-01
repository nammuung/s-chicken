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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherMapper weatherMapper;
    @Value("${weather.api.key}")
    private String apiKey; // 발급받은 API key

    public void getWeatherData() {
        String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        try {
//            urlBuilder.append("?" + URLEncoder.encode("q", "UTF-8") + "=Koesan");
            urlBuilder.append("?" + URLEncoder.encode("appid", "UTF-8") + "=" + apiKey);
            urlBuilder.append("&" + URLEncoder.encode("lang", "UTF-8") + "=kr");
            urlBuilder.append("&" + URLEncoder.encode("units", "UTF-8") + "=metric");
            urlBuilder.append("&" + URLEncoder.encode("lat", "UTF-8") + "=37.4829");
            urlBuilder.append("&" + URLEncoder.encode("lon", "UTF-8") + "=126.7811");
            System.out.println("urlBuilder.toString() = " + urlBuilder.toString());
            RestTemplate restTemplate = new RestTemplate();
            OpenWeather response = restTemplate.getForObject(urlBuilder.toString(), OpenWeather.class);
            weatherMapper.insertWeather(response);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getWeatherDataList() {
        String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast";
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        try {
//            urlBuilder.append("?" + URLEncoder.encode("q", "UTF-8") + "=Koesan");
            urlBuilder.append("?" + URLEncoder.encode("appid", "UTF-8") + "=" + apiKey);
            urlBuilder.append("&" + URLEncoder.encode("lang", "UTF-8") + "=kr");
            urlBuilder.append("&" + URLEncoder.encode("units", "UTF-8") + "=metric");
            urlBuilder.append("&" + URLEncoder.encode("lat", "UTF-8") + "=37.4829");
            urlBuilder.append("&" + URLEncoder.encode("lon", "UTF-8") + "=126.7811");
            System.out.println("urlBuilder.toString() = " + urlBuilder.toString());
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(urlBuilder.toString(), String.class);
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(response.getBody());
            System.out.println("data = " + data);
            JSONArray list = (JSONArray) data.get("list");
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

//            List<OpenWeather> weathers = mapper.readValue(list.toString(), new TypeReference<List<OpenWeather>>() {});
//            for(OpenWeather weather : weathers){
//                weatherMapper.insertWeather(weather);
//                System.out.println("weather = " + weather);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
