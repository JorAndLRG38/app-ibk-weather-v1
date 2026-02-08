package com.ibk.weather.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErrorMapper {

    private static final Pattern CODE_PATTERN = Pattern.compile("^\\s*(\\d{3})");
    private final Map<Integer, String> messages = new HashMap<>();

    public ErrorMapper() {
        messages.put(400, "Hubo problemas con la solicitud de %c. Verifique los parámetros y vuelva a intentarlo.");
        messages.put(401, "No autorizado. Necesitas autenticación para acceder a los datos de %c.");
        messages.put(403, "No permitido. No tienes permisos para acceder a la informacion de %c.");
        messages.put(404, "No encontrado. La ciudad %c no fue ubicada en nuestro sistema.");
        messages.put(429, "Demasiadas solicitudes para %c. Intenta de nuevo más tarde.");
        messages.put(500, "Error interno del servidor. Intenta nuevamente más tarde.");
    }

    public String getMessageFromString(String input, String city) {
        if (input == null || input.trim().isEmpty()) {
            return "Código no disponible.";
        }
        Matcher m = CODE_PATTERN.matcher(input.trim());
        if (!m.find()) {
            return "Código no encontrado en la cadena.";
        }
        try {
            int code = Integer.parseInt(m.group(1));
            return messages.getOrDefault(
                code, "Error HTTP " + code + ". Consulte la documentación del API.")
                .replace("%c", city);
        } catch (NumberFormatException e) {
            return "Código inválido.";
        }
    }
}