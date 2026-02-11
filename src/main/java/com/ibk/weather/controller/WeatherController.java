package com.ibk.weather.controller;

import com.ibk.weather.models.responses.ServicesResponse;
import com.ibk.weather.services.OpenWeatherService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Controlador para manejar las solicitudes relacionadas con el clima.
 */
@RestController
@RequiredArgsConstructor
public class WeatherController {

  private final OpenWeatherService service;

  /**
   * GET /api/weather/current/{city} : Obtener clima actual
   * Devuelve el clima actual de una ciudad usando OpenWeatherMap.
   *
   * @param city Nombre de la ciudad (ej. Lima, London) (required)
   * @return Clima actual.
   *
   */
  @GetMapping("/api/weather/current/{city}")
  public Mono<ResponseEntity<ServicesResponse>> getCurrentWeather(
      @PathVariable String city,
      @Parameter(hidden = true) final ServerWebExchange exchange) {
    return service.getCurrentWeather(city, exchange);
  }
}