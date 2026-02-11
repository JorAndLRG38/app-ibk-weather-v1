package com.ibk.weather.mapper;

import com.ibk.weather.models.CurrentWeatherResponse;
import com.ibk.weather.models.responses.ServicesResponse;
import com.ibk.weather.models.responses.WeatherDtoResponse;
import com.ibk.weather.utils.HourUtils;
import com.ibk.weather.utils.WeatherTranslator;

/**
 * Mapper para convertir las respuestas de OpenWeatherMap a la estructura de respuesta
 * definida por el microservicio.
 */
public class ResponseMapper {

  private final WeatherTranslator translations = new WeatherTranslator();

  /**
   * Mapea la respuesta de OpenWeatherMap a la estructura de ServicesResponse.
   *
   * @param obj Respuesta de OpenWeatherMap.
   * @return ServicesResponse con los datos mapeados.
   */
  public ServicesResponse mapToServicesResponse(CurrentWeatherResponse obj) {
    ServicesResponse response = new ServicesResponse();
    response.setId(obj.getName());
    WeatherDtoResponse weatherDto = new WeatherDtoResponse();
    weatherDto.setCoordinates("lat: " + obj.getCoord().getLat()
        + ", lon: " + obj.getCoord().getLon());
    weatherDto.setLocation(obj.getName() + ", " + obj.getSys().getCountry());
    weatherDto.setTemperature(obj.getMain().getTemp() + "°C");
    weatherDto.setTempSensation(obj.getMain().getFeelsLike() + "°C");
    weatherDto.setDescription(
        translations.translate(obj.getWeather().get(0).getDescription()));
    weatherDto.setRange(obj.getMain().getTempMin() + "°C - "
        + obj.getMain().getTempMax() + "°C");
    weatherDto.setHumidity(obj.getMain().getHumidity() + "%");
    weatherDto.setWindSpeed(obj.getWind().getSpeed() + "m/s");
    weatherDto.setPressure(obj.getMain().getPressure() + "hPa");
    response.setResultWeather(weatherDto);
    response.setSlot(HourUtils.currentSlot());
    return response;
  }
}
