package com.ibk.weather.controller;

import com.ibk.weather.models.CurrentWeatherResponse;
import com.ibk.weather.models.Error;
import com.ibk.weather.models.ForecastResponse;
import com.ibk.weather.services.OpenWeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private OpenWeatherService service;

    /**
     * GET /api/weather/current/{city} : Obtener clima actual
     * Devuelve el clima actual de una ciudad usando OpenWeatherMap.
     *
     * @param city Nombre de la ciudad (ej. Lima, London) (required)
     *
     * @return Clima actual en formato JSON (status code 200)
     *         or Parámetros inválidos (status code 400)
     */
    @Operation(
        operationId = "getCurrentWeather",
        summary = "Obtener clima actual",
        description = "Devuelve el clima actual de una ciudad usando OpenWeatherMap.",
        tags = { "weather" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Clima actual en formato JSON", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CurrentWeatherResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @GetMapping("/api/weather/current/{city}")
    public Mono<ResponseEntity<CurrentWeatherResponse>> getCurrentWeather(
        @NotNull @Parameter(name = "city", description = "Nombre de la ciudad (ej. Lima, London)", required = true, in = ParameterIn.PATH) @PathVariable("city") String city,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.OK);
        return result.then(Mono.empty());
    }

    /**
     * GET /api/weather/forecast/{city} : Obtener pronóstico de 5 días
     * Devuelve el pronóstico de 5 días (cada 3 horas) de una ciudad usando OpenWeatherMap.
     *
     * @param city Nombre de la ciudad (ej. Lima, London) (required)
     * @return Pronóstico en formato JSON (status code 200)
     *         or Parámetros inválidos (status code 400)
     */
    @Operation(
        operationId = "getForecast",
        summary = "Obtener pronóstico de 5 días",
        description = "Devuelve el pronóstico de 5 días (cada 3 horas) de una ciudad usando OpenWeatherMap.",
        tags = { "weather" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Pronóstico en formato JSON", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ForecastResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @GetMapping("/api/weather/forecast/{city}")
    public Mono<ResponseEntity<ForecastResponse>> getForecast(
        @NotNull @Parameter(name = "city", description = "Nombre de la ciudad (ej. Lima, London)", required = true, in = ParameterIn.PATH) @PathVariable("city") String city,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.OK);
        return result.then(Mono.empty());
    }
}