package com.ibk.weather.services.impl;

import com.ibk.weather.models.CurrentWeatherResponse;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Servicio para interactuar con Redis de manera reactiva.
 * Permite guardar y obtener datos de clima actual en Redis con una expiración de 1 hora.
 */
@Component
public class RedisServiceImpl {

  private final ReactiveRedisConnectionFactory factory;
  private final ReactiveRedisOperations<String, CurrentWeatherResponse> weatherOps;
  private final Logger log = LoggerFactory.getLogger(RedisServiceImpl.class);

  /**
   * Constructor para inyectar las dependencias de Redis.
   *
   * @param factory Fábrica de conexiones reactivas a Redis.
   * @param weatherOps Operaciones reactivas para manejar objetos CurrentWeatherResponse en Redis.
   */
  public RedisServiceImpl(ReactiveRedisConnectionFactory factory,
                          ReactiveRedisOperations<String, CurrentWeatherResponse> weatherOps) {
    this.factory = factory;
    this.weatherOps = weatherOps;
  }

  /**
   * Guardar los datos de clima actual en Redis con una clave basada en el nombre de la ciudad.
   * La entrada expirará después de 1 hora.
   *
   * @param response Objeto CurrentWeatherResponse que contiene los datos del clima actual.
   */
  public void saveData(CurrentWeatherResponse response) {
    weatherOps.opsForValue()
        .setIfAbsent(response.getName(), response, Duration.ofHours(1L))
        .subscribe(success -> {
          if (success) {
            log.info("Datos guardados en cache con codigo {}.", response.getName());
          } else {
            log.info("Datos ya existen en cache para codigo {}. No se guardaron.", response.getName());
          }
        }, error ->
            log.error("Error al guardar datos en cache para codigo {}: {}", response.getName(), error.getMessage()));
  }

  /**
   * Obtener los datos de clima actual de Redis utilizando el nombre de la ciudad como clave.
   *
   * @param name Nombre de la ciudad (clave para Redis).
   * @return Mono con el objeto CurrentWeatherResponse.
   */
  public Mono<CurrentWeatherResponse> getData(String name) {
    log.info("Buscando datos en cache con codigo {}.", name);
    return weatherOps.opsForValue()
        .get(name)
        .doOnSuccess(success ->
            log.info("Datos obtenidos de cache para codigo {}.", name))
        .doOnError(ex -> log.error("Error al obtener datos: {}", ex.getMessage()));
  }
}