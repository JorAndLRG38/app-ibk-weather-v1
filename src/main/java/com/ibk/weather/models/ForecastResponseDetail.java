package com.ibk.weather.models;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ForecastResponseDetail {

    private final @Nullable String datetime;
    private final @Nullable Integer temperature;
    private final @Nullable String description;
}
