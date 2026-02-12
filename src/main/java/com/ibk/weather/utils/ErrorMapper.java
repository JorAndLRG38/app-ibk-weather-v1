package com.ibk.weather.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que mapea códigos de error HTTP a mensajes personalizados.
 */
public class ErrorMapper {

  private static final Pattern CODE_PATTERN = Pattern.compile("^\\s*(\\d{3})");
  private final Map<Integer, String> messages = new HashMap<>();
  private final Logger log = LoggerFactory.getLogger(ErrorMapper.class);

  /**
   * Constructor que inicializa el mapa de codigos de error con mensajes personalizados.
   */
  public ErrorMapper() {
    messages.put(400, "Hubo problemas con la solicitud de %c. "
        + "Verifique los parámetros y vuelva a intentarlo.");
    messages.put(401, "No autorizado. Necesitas autenticación para acceder a los datos de %c.");
    messages.put(403, "No permitido. No tienes permisos para acceder a la informacion de %c.");
    messages.put(404, "No encontrado. La ciudad %c no fue ubicada en nuestro sistema.");
    messages.put(429, "Demasiadas solicitudes para %c. Intenta de nuevo más tarde.");
    messages.put(500, "Error interno del servidor. Intenta nuevamente más tarde.");
  }

  /**
   * Metodo que extrae el codigo de error de una cadena de entrada
   * y devuelve un mensaje personalizado.
   *
   * @param input La cadena de entrada que contiene el código de error.
   * @param city  El nombre de la ciudad para personalizar el mensaje.
   * @return Un mensaje personalizado basado en el código de error encontrado en la cadena.
   */
  public String getMessageFromString(String input, String city) {
    if (input == null || input.trim().isEmpty()) {
      return "Código no disponible.";
    }
    log.error("Error recibido: '{}'", input.trim());
    Matcher m = CODE_PATTERN.matcher(input.trim());
    if (!m.find()) {
      log.error("No se encontró un código de error en la cadena: '{}'", input.trim());
      return "Código " + input + " no encontrado en la cadena.";
    }
    try {
      int code = Integer.parseInt(m.group(1));
      log.error("Error de código: " +  code);
      return messages.getOrDefault(
          code, "Error HTTP " + code + ". Consulte la documentación del API.")
          .replace("%c", city);
    } catch (NumberFormatException e) {
      return "Código inválido: " + e.getMessage();
    }
  }
}