package com.ibk.weather.clients;

import com.ibk.weather.models.CurrentWeatherResponse;
import com.ibk.weather.models.ForecastResponse;
import com.ibk.weather.config.OpenWeatherConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class OpenWeatherClient {

    private final OpenWeatherConfig config;
    private final WebClient webClient;

    public OpenWeatherClient(WebClient.Builder builder, OpenWeatherConfig config) {
        this.config = config;
        this.webClient = builder.baseUrl(config.getBaseUrl()).build();
    }

    public Mono<CurrentWeatherResponse> getCurrentWeather(String city) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/weather")
                        .queryParam("q", city)
                        .queryParam("appid", config.getApiKey())
                        .queryParam("units", "metric")
                        .build())
                .retrieve()
                .bodyToMono(CurrentWeatherResponse.class);
    }

    public Mono<ForecastResponse> getForecast(String city) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/forecast")
                        .queryParam("q", city)
                        .queryParam("appid", config.getApiKey())
                        .queryParam("units", "metric")
                        .build())
                .retrieve()
                .bodyToMono(ForecastResponse.class);
    }

}
