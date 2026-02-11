package com.ibk.weather.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO de respuesta para el endpoint de servicios del microservicio.
 * Contiene información sobre el resultado del servicio.
 */
@AllArgsConstructor
@Builder
@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServicesResponse {

  @JsonProperty("id")
  public String id;
  @JsonProperty("resultWeather")
  public WeatherDtoResponse resultWeather;
  @JsonProperty("slot")
  public int slot;
  @JsonProperty("error")
  public Object error;

  /**
   * Metodo de conveniencia para crear una respuesta de error.
   *
   * @param id Identificador del servicio que causó el error.
   * @param errorMessage Mensaje de error descriptivo.
   * @return Instancia de ServicesResponse con el error configurado.
   */
  public ServicesResponse errorResponse(String id, String errorMessage) {
    this.id = id;
    this.error = errorMessage;
    return this;
  }
}