package com.ibk.weather.services;

import com.ibk.weather.models.CurrentWeatherResponse;
import com.ibk.weather.models.ForecastResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface OpenWeatherService {

    Mono<ResponseEntity<CurrentWeatherResponse>> getCurrentWeather(String city);

    Mono<ResponseEntity<ForecastResponse>> getForecast(String id);

}
