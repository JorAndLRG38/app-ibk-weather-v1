package com.ibk.weather.clients;

import com.ibk.weather.config.OpenWeatherConfig;
import com.ibk.weather.models.CurrentWeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Cliente para consumir la API de OpenWeatherMap y obtener datos meteorológicos.
 */
@Component
public class OpenWeatherClient {

  private OpenWeatherConfig config;
  private final WebClient webClient;
  private final Logger log = LoggerFactory.getLogger(OpenWeatherClient.class);

  /**
   * Constructor para inicializar el cliente con la configuración de OpenWeather.
   *
   * @param config Configuración con la URL base y la clave de API.
   */
  public OpenWeatherClient(OpenWeatherConfig config) {
    this.config = config;
    this.webClient = WebClient.builder().baseUrl(config.getBaseUrl()).build();
  }

  /**
   * Obtener el clima actual de una ciudad utilizando la API de OpenWeatherMap.
   *
   * @param city Nombre de la ciudad (ej. Lima, London)
   * @return Mono con la respuesta del clima actual.
   */
  public Mono<CurrentWeatherResponse> getCurrentWeather(String city) {
    log.info("Datos no obtenidos de cache. Llamando a OpenWeather API "
        + "para obtener el clima de la ciudad de {}", city);
    return webClient.get()
        .uri(uriBuilder ->
            uriBuilder.path("/weather")
            .queryParam("q", city)
            .queryParam("appid", config.getApiKey())
            .queryParam("units", "metric")
            .build())
        .retrieve()
        .bodyToMono(CurrentWeatherResponse.class);
  }
}
