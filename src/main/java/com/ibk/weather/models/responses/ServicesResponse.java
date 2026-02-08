package com.ibk.weather.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JsonProperty("error")
    public Object error;

    public ServicesResponse errorResponse(String id, String errorMessage) {
        this.id = id;
        this.error = errorMessage;
        return this;
    }
}