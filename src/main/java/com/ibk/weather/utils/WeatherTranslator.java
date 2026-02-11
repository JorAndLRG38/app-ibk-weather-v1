package com.ibk.weather.utils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Clase para traducir descripciones de clima del inglés al español.
 * Utiliza un mapa de traducciones predefinidas para convertir las descripciones.
 */
public class WeatherTranslator {

  private final Map<String, String> translations = new HashMap<>();

  /** Constructor que inicializa el mapa de traducciones con las descripciones comunes del clima. */
  public WeatherTranslator() {
    translations.put("clear sky", "Cielo despejado");
    translations.put("few clouds", "Cielo ligeramente cubierto");
    translations.put("scattered clouds", "Cielo parcialmente cubierto");
    translations.put("broken clouds", "Cielo nuboso");
    translations.put("shower rain", "Lluvia fuerte");
    translations.put("rain", "Lluvia moderada");
    translations.put("thunderstorm", "Tormenta");
    translations.put("snow", "Nevada");
    translations.put("mist", "Neblina");
  }

  /** Método para traducir una descripción de clima del inglés al español. */
  public String translate(String description) {
    if (description == null) {
      return "Descripción desconocida";
    }
    String key = description.toLowerCase(Locale.ROOT).trim();
    return translations.getOrDefault(key, "Descripción no traducida");
  }
}