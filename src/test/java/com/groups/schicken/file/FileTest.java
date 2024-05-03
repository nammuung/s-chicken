package com.groups.schicken.file;

import com.groups.schicken.common.util.DateManager;
import com.groups.schicken.common.util.FileManager;
import com.groups.schicken.common.vo.FileVO;
import com.groups.schicken.erp.product.ProductService;
import com.groups.schicken.erp.product.ProductVO;
import com.groups.schicken.erp.product.StockService;
import com.groups.schicken.erp.product.StockVO;
import com.groups.schicken.franchise.FranchiseVO;
import com.groups.schicken.franchise.sales.Generator;
import com.groups.schicken.franchise.sales.Sales;
import com.groups.schicken.franchise.sales.SalesMapper;
import com.groups.schicken.weather.OpenWeather;
import com.groups.schicken.weather.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest(properties = "spring.profiles.include=dev")
public class FileTest {
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private Generator generator;
    @Autowired
    private TestMapper mapper;
    @Autowired
    private SalesMapper salesMapper;
    @Test
    public void test() throws Exception {
//        List<OpenWeather> list = weatherService.getWeatherList();
//        for(OpenWeather weather : list){
//            System.out.println("weather = " + weather);
//        }

//        OpenWeather weather = weatherService.getWeather();
//        System.out.println("weather = " + weather);
//        for(Map<?,?> map : generator.get()){
//            mapper.insertMenu(map);
//        }
        List<Sales.Detail> data = generator.get();
        salesMapper.addListSalesDetail(data);
        List<Sales> list = new ArrayList<>();

        for(Sales.Detail map : data){
            Sales sales = map.getSales();
            list.add(sales);
        }
        salesMapper.addListSales(list);

    }
}
