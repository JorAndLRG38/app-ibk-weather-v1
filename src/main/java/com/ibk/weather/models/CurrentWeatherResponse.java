package com.ibk.weather.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase que representa la respuesta de la API de OpenWeatherMap para el clima actual.
 */
@Getter
@Setter
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class CurrentWeatherResponse {

  private Coord coord;
  private List<Weather> weather;
  private String base;
  private Main main;
  private int visibility;
  private Wind wind;
  private Clouds clouds;
  private long dt;
  private Sys sys;
  private int timezone;
  private long id;
  private String name;
  private int cod;

  /**
   * Clase interna que representa las coordenadas geográficas (latitud y longitud).
   */
  @Getter
  @Setter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Coord {
    private double lon;
    private double lat;
  }

  /**
   * Clase interna que representa las condiciones climáticas.
   */
  @Getter
  @Setter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Weather {
    private int id;
    private String main;
    private String description;
    private String icon;
  }

  /**
   * Clase interna que representa los datos principales del clima.
   */
  @Getter
  @Setter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Main {
    @JsonProperty("temp")
    private double temp;
    @JsonProperty("feels_like")
    private double feelsLike;
    @JsonProperty("temp_min")
    private double tempMin;
    @JsonProperty("temp_max")
    private double tempMax;
    @JsonProperty("pressure")
    private int pressure;
    @JsonProperty("humidity")
    private int humidity;
    @JsonProperty("sea_level")
    private int seaLevel;
    @JsonProperty("grnd_level")
    private int grndLevel;
  }

  /**
   * Clase interna que representa los datos del viento.
   */
  @Getter
  @Setter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Wind {
    @JsonProperty("speed")
    private double speed;
    @JsonProperty("deg")
    private int deg;
  }

  /**
   * Clase interna que representa los datos de nubosidad.
   */
  @Getter
  @Setter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Clouds {
    @JsonProperty("all")
    private int all;
  }

  /**
   * Clase interna que representa los datos del sistema (país, amanecer, atardecer).
   */
  @Getter
  @Setter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Sys {
    @JsonProperty("type")
    private int type;
    @JsonProperty("id")
    private int id;
    @JsonProperty("country")
    private String country;
    @JsonProperty("sunrise")
    private long sunrise;
    @JsonProperty("sunset")
    private long sunset;
  }
}