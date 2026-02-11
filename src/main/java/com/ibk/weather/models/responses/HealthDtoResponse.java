package com.ibk.weather.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO de respuesta para el endpoint de salud del microservicio.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class HealthDtoResponse {
  private String status;
  private String message;
}