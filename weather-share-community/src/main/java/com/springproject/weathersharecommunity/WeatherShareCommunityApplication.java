package com.springproject.weathersharecommunity;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.springproject.weathersharecommunity.domain.Member;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication

public class WeatherShareCommunityApplication {

	//application.yml 과 aws.yml 모두를 설정 파일로 읽어서 사용
	public static final String APPLICATION_LOCATIONS =
			"spring.config.location=" + "classpath:application.yml,"
			+ "classpath:aws.yml";

	@Bean
	Hibernate5Module hibernate5Module(){
		return new Hibernate5Module();
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(WeatherShareCommunityApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}

}
