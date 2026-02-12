package com.ibk.weather.services.impl;

import com.ibk.weather.models.CurrentWeatherResponse;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Servicio para interactuar con Redis de manera reactiva.
 * Permite guardar y obtener datos de clima actual en Redis con una expiración de 1 hora.
 */
@Repository
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
        .setIfAbsent(this.capitalize(response.getName()),
            response, Duration.ofMinutes(1L))
        .subscribe();
  }

  /**
   * Obtener los datos de clima actual de Redis utilizando el nombre de la ciudad como clave.
   *
   * @param name Nombre de la ciudad (clave para Redis).
   * @return Mono con el objeto CurrentWeatherResponse.
   */
  public Mono<CurrentWeatherResponse> getData(String name) {
    log.info("Buscando datos en cache con codigo {}.", name);
    return weatherOps.opsForValue().get(this.capitalize(name));
  }

  /**
   * Metodo para estandarizar el formato del id.
   *
   * @param input Nombre de la ciudad.
   * @return String formateado.
   */
  private String capitalize(String input) {
    if (input == null || input.isEmpty()) {
      return input;
    }
    return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
  }
}