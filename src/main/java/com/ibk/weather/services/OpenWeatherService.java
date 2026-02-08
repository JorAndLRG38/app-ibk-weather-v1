package com.ibk.weather.services;

import com.ibk.weather.models.responses.ServicesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public interface OpenWeatherService {

    Mono<ResponseEntity<ServicesResponse>> getCurrentWeather(
        String city, ServerWebExchange exchange);

}
