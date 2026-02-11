package com.ibk.weather.services;

import com.ibk.weather.models.responses.HealthDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Interfaz para el servicio de salud del microservicio.
 * Define el contrato para verificar el estado del microservicio.
 */
public interface HealthService {

  /**
   * Verificar el estado del microservicio.
   *
   * @param exchange Contexto de la solicitud web.
   * @return Mono con la respuesta de salud del microservicio.
   */
  Mono<ResponseEntity<HealthDtoResponse>> checkHealth(ServerWebExchange exchange);
}
