package com.groups.schicken.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OpenWeather {
    private List<Weather> weather;
    private String base;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private Rain rain;
    private Snow snow;
    private int visibility;
    private long dt;
    private int timezone;
    private long id;
    private String name;
    private int cod;
    @Data
    public static class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;
    }

    @Data
    public static class Main {
        private float temp;
        private float feels_like;
        private float temp_min;
        private float temp_max;
        private int pressure;
        private float humidity;
        private float sea_level;
        private float grnd_level;
    }

    @Data
    public static class Wind {
        private float speed;
        private int deg;
        private float gust;
    }

    @Data
    public static class Clouds {
        private int all;
    }

    @Data
    public static class Rain {
        @JsonProperty("1h")
        private float rain1h;
        @JsonProperty("3h")
        private float rain3h;
    }

    @Data
    public static class Snow {
        @JsonProperty("1h")
        private float snow1h;
        @JsonProperty("3h")
        private float snow3h;
    }

    @Data
    public static class Sys {
        private int type;
        private int id;
        private String country;
        private long sunrise;
        private long sunset;
    }

    private String getWindDirection(int degree) {
        int result = (int)((degree + 22.5 * 0.5) / 22.5);
        WindType windType = WindType.value(result);
        return windType.getName();
    }
}