package com.ibk.weather.services.impl;

import com.ibk.weather.models.CurrentWeatherResponse;
import com.ibk.weather.clients.OpenWeatherClient;
import com.ibk.weather.models.responses.ServicesResponse;
import com.ibk.weather.models.responses.WeatherDtoResponse;
import com.ibk.weather.services.OpenWeatherService;
import com.ibk.weather.utils.ErrorMapper;
import com.ibk.weather.utils.WeatherTranslator;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Service
public class OpenWeatherServiceImpl implements OpenWeatherService {

    private final OpenWeatherClient weatherClient;
    private final ErrorMapper errorMapper = new ErrorMapper();
    private final WeatherTranslator translations = new WeatherTranslator();

    private final Logger log = LoggerFactory.getLogger(OpenWeatherServiceImpl.class);
    public OpenWeatherServiceImpl(OpenWeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    @Override
    public Mono<ResponseEntity<ServicesResponse>> getCurrentWeather(String city, ServerWebExchange exchange) {
        log.info("Received request for current weather in city: {}", city);
        return weatherClient.getCurrentWeather(city)
            .map(json -> ResponseEntity.ok(this.buildResponse(json)))
            .onErrorResume(ex ->
                Mono.just(ResponseEntity
                    .status(500).body(new ServicesResponse().errorResponse(
                        city, errorMapper.getMessageFromString(ex.getMessage(), city)))));
    }

    private ServicesResponse buildResponse(CurrentWeatherResponse obj) {
        log.info("Building response for city: {}", obj.getName());
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
        return response;
    }
}