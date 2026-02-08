package com.ibk.weather.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuración para la API de OpenWeatherMap.
 * Permite cargar la URL base y la clave de API desde el archivo de propiedades.
 */
@Component
@ConfigurationProperties(prefix = "openweather")
@Getter
@Setter
public class OpenWeatherConfig {
  private String baseUrl;
  private String apiKey;
}