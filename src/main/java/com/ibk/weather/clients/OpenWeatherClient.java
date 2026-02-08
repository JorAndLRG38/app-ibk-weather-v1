package com.ibk.weather.clients;

import com.ibk.weather.config.OpenWeatherConfig;
import com.ibk.weather.models.CurrentWeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class OpenWeatherClient {

    private OpenWeatherConfig config;
    private final WebClient webClient;
    private final Logger log = LoggerFactory.getLogger(OpenWeatherClient.class);

    public OpenWeatherClient(OpenWeatherConfig config) {
        this.config = config;
        this.webClient = WebClient.builder().baseUrl(config.getBaseUrl()).build();
    }

    public Mono<CurrentWeatherResponse> getCurrentWeather(String city) {
        return webClient.get()
            .uri(uriBuilder ->
                uriBuilder.path("/weather")
                .queryParam("q", city)
                .queryParam("appid", config.getApiKey())
                .queryParam("units", "metric")
                .build())
            .retrieve()
            .bodyToMono(CurrentWeatherResponse.class);
    }
}
