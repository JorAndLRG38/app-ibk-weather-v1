package com.ibk.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *  Clase principal de la aplicación Spring Boot para el microservicio de clima.
 *  Configura el contexto de la aplicación, habilita el caching y la programación de tareas
 */
@EnableCaching
@EnableScheduling
@SpringBootApplication
public class AppIbkWeatherV1Application {

  /**
   * Metodo principal que inicia la aplicación Spring Boot.
   *
   * @param args Argumentos de línea de comandos (no se utilizan)
   */
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger(AppIbkWeatherV1Application.class);
    SpringApplication.run(AppIbkWeatherV1Application.class, args);
    log.info("Iniciando aplicativo.");
  }
}