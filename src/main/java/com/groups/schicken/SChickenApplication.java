package com.groups.schicken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication//(exclude = { SecurityAutoConfiguration.class })
@EnableCaching
@EnableScheduling
public class SChickenApplication {

	public static void main(String[] args) {
		SpringApplication.run(SChickenApplication.class, args);
	}

}
