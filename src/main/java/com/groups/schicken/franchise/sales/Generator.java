package com.groups.schicken.franchise.sales;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
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
    public List<Map<?,?>> get() {

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
            List<Map<?, ?>> data = gson.fromJson(jsonOutput, type);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
