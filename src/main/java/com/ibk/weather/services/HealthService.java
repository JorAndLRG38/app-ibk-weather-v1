package com.ibk.weather.services;

import com.ibk.weather.models.responses.HealthDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public interface HealthService {

    Mono<ResponseEntity<HealthDtoResponse>> checkHealth(ServerWebExchange exchange);
}
