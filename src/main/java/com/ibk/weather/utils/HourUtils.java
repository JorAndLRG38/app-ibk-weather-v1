package com.ibk.weather.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Utilidades para manejar horas y slots de tiempo.
 */
public final class HourUtils {

  private HourUtils() {}

  /**
   * Obtiene el slot de 3 horas actual basado en la hora del día.
   **/
  public static int currentSlot() {
    int hour = LocalDateTime.now().getHour();
    return hour / 3;
  }
}