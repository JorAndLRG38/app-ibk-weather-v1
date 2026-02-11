package com.ibk.weather.services;

import com.ibk.weather.models.responses.ServicesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Interfaz para el servicio de clima que interactúa con OpenWeatherMap.
 * Define el contrato para obtener el clima actual de una ciudad.
 */
public interface OpenWeatherService {

  /**
   * Obtener el clima actual de una ciudad usando OpenWeatherMap.
   *
   * @param city Nombre de la ciudad (ej. Lima, London).
   * @param exchange Contexto de la solicitud web.
   * @return Mono con la respuesta del clima actual.
   */
  Mono<ResponseEntity<ServicesResponse>> getCurrentWeather(
      String city, ServerWebExchange exchange);

}
