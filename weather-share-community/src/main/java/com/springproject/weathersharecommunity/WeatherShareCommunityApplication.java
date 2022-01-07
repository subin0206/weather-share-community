package com.springproject.weathersharecommunity;

import com.springproject.weathersharecommunity.domain.Member;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication

public class WeatherShareCommunityApplication {
	public static void main(String[] args) {
		SpringApplication.run(WeatherShareCommunityApplication.class, args);
	}

}
