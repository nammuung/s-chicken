package com.groups.schicken.franchise.sales;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.groups.schicken.weather.OpenWeather;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class Generator {
    private final ResourceLoader resourceLoader;
    public Generator(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    public List<Sales.Detail> get() {

        Resource resource = resourceLoader.getResource("classpath:script.py");

        try {
            String scriptPath = resource.getFile().getAbsolutePath();
            String[] command = new String[]{"python3", scriptPath, "10", "20"};
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Exited with code : " + exitCode);

            String jsonOutput = builder.toString();
            Gson gson = new Gson();
            Type type = new TypeToken<List<Map<?,?>>>(){}.getType();
            Type type2 = new TypeToken<List<Sales.Detail>>(){}.getType();
//            System.out.println("jsonOutput = " + jsonOutput);
            List<Map<?, ?>> data = gson.fromJson(jsonOutput, type);
            List<Sales.Detail> test = gson.fromJson(jsonOutput, type2);
//            JSONParser parser = new JSONParser();
//            JSONObject data = (JSONObject) parser.parse(jsonOutput);
//            JSONArray list = (JSONArray) data.get("detail");
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

//            List<OpenWeather> weathers = mapper.readValue(list.toString(), new TypeReference<List<OpenWeather>>() {});
            return test;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
