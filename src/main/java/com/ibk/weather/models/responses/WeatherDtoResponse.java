package com.ibk.weather.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO de respuesta para el endpoint de clima del microservicio.
 * Contiene información detallada sobre el clima en una ubicación específica.
 */
@Getter
@Setter
public class WeatherDtoResponse {

  @JsonProperty("coordinates")
  private String coordinates;
  @JsonProperty("location")
  private String location;
  @JsonProperty("temperature")
  private String temperature;
  @JsonProperty("range")
  private String range;
  @JsonProperty("tempSensation")
  private String tempSensation;
  @JsonProperty("description")
  private String description;
  @JsonProperty("humidity")
  private String humidity;
  @JsonProperty("windSpeed")
  private String windSpeed;
  @JsonProperty("pressure")
  private String pressure;
}