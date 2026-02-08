package com.ibk.weather.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
@RequiredArgsConstructor
public class CurrentWeatherResponse {

    private @Nullable String city;
    private @Nullable Float temperature;
    private @Nullable String description;
    private @Nullable Integer humidity;
    private @Nullable Float windSpeed;
    private @Nullable Object raw;
}
