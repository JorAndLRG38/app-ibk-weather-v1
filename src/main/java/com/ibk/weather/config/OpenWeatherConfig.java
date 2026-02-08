package com.ibk.weather.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "openweather")
@Getter
@Setter
public class OpenWeatherConfig {

    private String baseUrl;
    private String apiKey;

}