package com.groups.schicken.file;

import com.groups.schicken.common.util.DateManager;
import com.groups.schicken.common.util.FileManager;
import com.groups.schicken.common.vo.FileVO;
import com.groups.schicken.erp.product.ProductService;
import com.groups.schicken.erp.product.ProductVO;
import com.groups.schicken.erp.product.StockService;
import com.groups.schicken.erp.product.StockVO;
import com.groups.schicken.weather.OpenWeather;
import com.groups.schicken.weather.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(properties = "spring.profiles.include=dev")
public class FileTest {
    @Autowired
    private WeatherService weatherService;

    @Test
    public void test() throws Exception {
//        List<OpenWeather> list = weatherService.getWeatherList();
//        for(OpenWeather weather : list){
//            System.out.println("weather = " + weather);
//        }

        OpenWeather weather = weatherService.getWeather();
        System.out.println("weather = " + weather);
    }
}
