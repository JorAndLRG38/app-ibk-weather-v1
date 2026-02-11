package com.ibk.weather.controller;

import com.ibk.weather.models.responses.HealthDtoResponse;
import com.ibk.weather.services.HealthService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Controlador REST para el endpoint de salud del microservicio.
 */
@RestController
@Validated
@RequiredArgsConstructor
public class HealthController {

  private final HealthService service;

  /**
   * GET /api/health : Verificar estado del microservicio
   * Endpoint de salud para comprobar que el microservicio está corriendo.
   *
   * @return Estado OK (status code 200)
   */
  @GetMapping("api/health")
  public Mono<ResponseEntity<HealthDtoResponse>> getHealth(
      @Parameter(hidden = true) final ServerWebExchange exchange) {
    return service.checkHealth(exchange);
  }
}
