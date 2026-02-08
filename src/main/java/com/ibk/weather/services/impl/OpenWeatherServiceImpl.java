package com.ibk.weather.services.impl;

import com.ibk.weather.models.CurrentWeatherResponse;
import com.ibk.weather.models.ForecastResponse;
import com.ibk.weather.clients.OpenWeatherClient;
import com.ibk.weather.services.OpenWeatherService;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@NoArgsConstructor
public class OpenWeatherServiceImpl implements OpenWeatherService {

    private OpenWeatherClient weatherClient;

    @Override
    public Mono<ResponseEntity<CurrentWeatherResponse>> getCurrentWeather(String city) {
        return weatherClient.getCurrentWeather(city)
            .map(json -> {
                CurrentWeatherResponse response = new CurrentWeatherResponse();
                response.setCity(city);
                response.setRaw(json);
                return ResponseEntity.ok(response);
            })
            .onErrorResume(ex -> Mono.just(ResponseEntity.status(500).build()));

    }

    @Override
    public Mono<ResponseEntity<ForecastResponse>> getForecast(String city) {
        return weatherClient.getForecast(city)
            .map(ResponseEntity::ok)
            .onErrorResume(ex -> {
                if (ex instanceof IllegalArgumentException) {
                    return Mono.just(ResponseEntity.badRequest().build());
                }
                return Mono.just(ResponseEntity.status(500).build());
            });

    }
}