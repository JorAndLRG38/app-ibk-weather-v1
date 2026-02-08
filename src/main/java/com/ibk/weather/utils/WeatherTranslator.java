package com.ibk.weather.utils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class WeatherTranslator {

    private final Map<String, String> translations = new HashMap<>();

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

    public String translate(String description) {
        if (description == null) return "Descripción desconocida";
        String key = description.toLowerCase(Locale.ROOT).trim();
        return translations.getOrDefault(key, "Descripción no traducida");
    }
}