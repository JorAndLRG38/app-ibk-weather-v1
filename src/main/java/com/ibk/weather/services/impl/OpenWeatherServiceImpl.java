package com.ibk.weather.services.impl;

import com.ibk.weather.clients.OpenWeatherClient;
import com.ibk.weather.mapper.ResponseMapper;
import com.ibk.weather.metrics.WeatherMetrics;
import com.ibk.weather.models.CurrentWeatherResponse;
import com.ibk.weather.models.responses.ServicesResponse;
import com.ibk.weather.services.OpenWeatherService;
import com.ibk.weather.utils.ErrorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Implementación del servicio de clima que interactúa con OpenWeatherMap.
 * Proporciona la lógica para obtener el clima actual de una ciudad, utilizando Redis para caché.
 */
@Service
public class OpenWeatherServiceImpl implements OpenWeatherService {

  private WeatherMetrics metrics;
  private final RedisServiceImpl redisService;
  private final OpenWeatherClient weatherClient;
  private final Logger log = LoggerFactory.getLogger(OpenWeatherServiceImpl.class);

  /**
   * Constructor para inyectar las dependencias del cliente de OpenWeather y el servicio de Redis.
   *
   * @param weatherClient Cliente para consumir la API de OpenWeatherMap.
   * @param redisService Servicio para interactuar con Redis y manejar la caché.
   */
  public OpenWeatherServiceImpl(OpenWeatherClient weatherClient,
                                RedisServiceImpl redisService) {
    this.weatherClient = weatherClient;
    this.redisService = redisService;
  }

  @Override
  public Mono<ResponseEntity<ServicesResponse>> getCurrentWeather(
      String city, ServerWebExchange exchange) {
    log.info("Obteniendo clima actual para la ciudad: {}", city);
    return this.getDataFromCacheOrApi(city)
        .map(json -> {
          metrics.recordSuccess();
          return ResponseEntity.ok(new ResponseMapper().mapToServicesResponse(json));})
        .onErrorResume(ex -> {
            metrics.recordError();
            return Mono.just(ResponseEntity
                .status(500).body(new ServicesResponse().errorResponse(
                    city, new ErrorMapper().getMessageFromString(ex.getMessage(), city))));
        });
  }

  private Mono<CurrentWeatherResponse> getDataFromCacheOrApi(String city) {
    log.info("Iniciando busqueda.");
    return redisService.getData(city)
      .map(apiResponse -> apiResponse)
      .switchIfEmpty(
        weatherClient.getCurrentWeather(city)
          .map(apiResponse -> {
            log.info("Datos obtenidos del API, procediendo con el guardado en cache");
            redisService.saveData(apiResponse);
            return apiResponse;
          })
      );
  }
}