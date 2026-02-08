package com.ibk.weather.services.impl;

import com.ibk.weather.models.responses.HealthDtoResponse;
import com.ibk.weather.services.HealthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Service
public class HealthServiceImpl implements HealthService {

    @Override
    public Mono<ResponseEntity<HealthDtoResponse>> checkHealth(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.OK);
        return Mono.just(
            ResponseEntity.status(200).body(new HealthDtoResponse(
                "200", "Funcionando correctamente")));
    }
}